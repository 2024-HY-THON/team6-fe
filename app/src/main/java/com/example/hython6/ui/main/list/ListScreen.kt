package com.example.hython6.ui.main.list

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.ViewTreeObserver
import android.view.WindowInsets
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hython6.data.RetrofitClient.getRetrofit
import com.example.hython6.data.remote.CategoryResponse
import com.example.hython6.data.remote.ListApiService
import com.example.hython6.ui.theme.Blue
import com.example.hython6.ui.theme.Gray2
import com.example.hython6.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen() {
    val listApiService: ListApiService = getRetrofit().create(ListApiService::class.java)
    val navController = rememberNavController()
    val interactionSource = remember { MutableInteractionSource() }
    var isEditing by remember { mutableStateOf(false) }
    var showMode by remember { mutableIntStateOf(1) }   // 1: 카테고리, 2: 검색 결과
    var selectedId by remember { mutableStateOf<Int?>(null) }
    var selectedName by remember { mutableStateOf("") }

    var isKeyboardVisible = remember {  // 키보드 상태 변수
        mutableStateOf(false)
    }
    keyBoardVisible(isKeyboardVisible)

    var categories by remember { mutableStateOf<List<CategoryResponse>?>(null) }
    LaunchedEffect(key1 = Unit){
        kotlin.runCatching {
            listApiService.getAllCategory("test1") // 테스트용 사용자 ID
        }.onSuccess { allCategory ->
            Log.d("CategoryResponse", "Categories: $allCategory")
            categories = allCategory
        }.onFailure { throwable ->
            Log.e("CategoryResponse", "Error fetching categories: ${throwable.message}")
            // 필요 시 에러 처리 로직 추가
        }
    }

    Scaffold (
        topBar = {
        },
        content = {
            NavHost(navController, startDestination = "list"){
                composable("list"){
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                    ){
                        // 검색바
                        SearchBar(showMode) { newShowMode ->
                            showMode = newShowMode // showMode 값 변경
                        }

                        // 카테고리 편집 버튼
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Text(
                                text = if (isEditing) "확인" else "목록 편집",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.Black,
                                ),
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                    ) { isEditing = !isEditing }
                                    .padding(end = 20.dp)
                            )
                        }

                        if (showMode == 1) {
                            // 카테고리 리스트
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(
                                    horizontal = 15.dp,
                                    vertical = 8.dp
                                ),
                            ) {
                                categories?.let { categoryList ->
                                    items(categoryList.size + 1) { index ->
                                        if (index < categoryList.size) {
                                            val category = categoryList[index]
                                            CategoryCard(
                                                categoryId = category.category_id,
                                                categoryName = category.category, // 서버에서 받은 category 값
                                                alarm = category.choose, // 서버에서 받은 choose 값
                                                isEditing = isEditing,
                                                navController,
                                                onClick = { clickedId, clickedCategory ->
                                                    Log.d("Category", "Clicked ID: $clickedId, Clicked Name: $clickedCategory")
                                                    selectedId = clickedId
                                                    selectedName = clickedCategory
                                                    Log.d("Category", "selectedId: $selectedId")
                                                    Log.d("Category", "selectedName: $selectedName")
                                                }
                                            )
                                        } else {
                                            AddCategoryDialog(selectedId, selectedName)
                                        }
                                    }
                                }
                            }
                        } else {
                            // 검색 결과 리스트
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(1),
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(
                                    horizontal = 15.dp,
                                    vertical = 8.dp
                                ),
                            ) {
                                items(15) { index -> // 15는 예시로 고정된 개수, 실제 데이터로 수정 예정
                                    SearchResCard("검색결과 $index", "사용자 $index")
                                }
                            }
                        }

                    }
                }
                composable("detail") { DetailScreen(categoryId = selectedId!!, categoryName = selectedName) }
            }

        }
    )
}

@Composable
fun keyBoardVisible(isKeyboardVisible: MutableState<Boolean>){
    val context = LocalContext.current
    val rootView = LocalView.current

    // 키보드의 가시성 확인
    DisposableEffect(context) {
        val callback = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ViewTreeObserver.OnPreDrawListener {
                val insets = rootView.rootWindowInsets
                isKeyboardVisible.value = insets.isVisible(WindowInsets.Type.ime())
                true
            }
        } else {
            ViewTreeObserver.OnGlobalLayoutListener {
                val rect = android.graphics.Rect()
                rootView.getWindowVisibleDisplayFrame(rect)
                val screenHeight = rootView.height
                val keypadHeight = screenHeight - rect.bottom
                isKeyboardVisible.value = keypadHeight > screenHeight * 0.15
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            rootView.viewTreeObserver.addOnPreDrawListener(callback as ViewTreeObserver.OnPreDrawListener)
        } else {
            rootView.viewTreeObserver.addOnGlobalLayoutListener(callback as ViewTreeObserver.OnGlobalLayoutListener)
        }

        onDispose {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                rootView.viewTreeObserver.removeOnPreDrawListener(callback as ViewTreeObserver.OnPreDrawListener)
            } else {
                rootView.viewTreeObserver.removeOnGlobalLayoutListener(callback as ViewTreeObserver.OnGlobalLayoutListener)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(showMode: Int, onShowModeChange: (Int) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        modifier = Modifier
            .height(110.dp)
            .padding(
                top = 30.dp,
                end = 5.dp,
            ),
        title = {
            TextField(
                value = searchText,
                onValueChange = {searchText = it},
                placeholder = {
                    Text(
                        text = "검색어를 입력하세요",
                        style = TextStyle(fontSize = 15.sp)
                    )
                },
                modifier = Modifier
                    .width(355.dp)
                    .height(70.dp)
                    .padding(
                        top = 10.dp,
                        end = 15.dp,
                    )
                    .background(White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Gray2, shape = RoundedCornerShape(8.dp)),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 15.sp),
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.padding(end = 3.dp),
                        onClick = {
                            onShowModeChange(2)
                            keyboardController?.hide()
                        },
                    ){
                        // 검색 아이콘
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Blue
                        )
                    }
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        // 키보드 Enter 키 눌렸을 때(검색 요청)
                        onShowModeChange(2)
                        keyboardController?.hide()
                    }
                ),
            )
        },
    )

    if(showMode == 2)
        BackHandler {
            onShowModeChange(1)
        }
}