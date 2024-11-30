package com.example.hython6.ui.main.list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import com.example.hython6.ui.theme.Gray2
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import com.example.hython6.data.RetrofitClient
import com.example.hython6.data.remote.CategoryRequest
import com.example.hython6.data.remote.ListApiService
import com.example.hython6.ui.theme.Blue

@Composable
fun AddCategoryDialog(selectedId: Int?, selectedName: String) {
    var isDialogOpen by remember { mutableStateOf(false) }  // Dialog 상태 관리
    var categoryName by remember { mutableStateOf(TextFieldValue("")) }  // 입력값 상태 관리
    var isCategoryAdded by remember { mutableStateOf(false) }

    // AddCategoryCard
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(160.dp)
            .fillMaxWidth()
            .clickable {
                isDialogOpen = true
            }
            .padding(vertical = 4.dp, horizontal = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray2,     // 카드 배경색
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center),
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Icon",
                tint = Color.Black
            )
        }
    }

    // Dialog 표시
    if (isDialogOpen) {
        AddCategoryDialog(
            categoryName = categoryName,
            onCategoryNameChange = { categoryName = it },
            // 취소 버튼 선택 시
            onDismissRequest = { isDialogOpen = false },
            // 확인 버튼 선택 시
            onConfirm = {
                Log.d("Category", "새로운 카테고리명: ${categoryName.text}")
                // 카테고리 생성 요청 API 호출
                isCategoryAdded = true
            }
        )
    }

    // LaunchedEffect를 사용하여 카테고리 추가 처리
    val listApiService: ListApiService = RetrofitClient.getRetrofit().create(ListApiService::class.java)
    if (isCategoryAdded) {
        LaunchedEffect(categoryName.text) {
            try {
                val categoryRequest = CategoryRequest(category = categoryName.text)
                val response = listApiService.addCategory("test1", categoryRequest)
                Log.d("Category", "카테고리 추가 성공: ${response.message}")
                Log.d("Category", "카테고리 추가 성공: ${response.category}")
                isDialogOpen = false    // 카테고리 추가 후 Dialog 닫기
            } catch (e: Exception) {
                Log.e("Category", "카테고리 추가 실패: ${e.message}")
            }
            isCategoryAdded = false     // 상태 초기화
        }
    }
}

@Composable
fun AddCategoryDialog(
    categoryName: TextFieldValue,
    onCategoryNameChange: (TextFieldValue) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "카테고리 추가")
        },
        text = {
            Column {
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    value = categoryName,
                    onValueChange = onCategoryNameChange,  // 입력값 변경
                    label = { Text("카테고리명") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                    contentColor = Color.White
                ),
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissRequest,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                    contentColor = Color.White
                ),
            ) {
                Text("취소")
            }
        }
    )
}

