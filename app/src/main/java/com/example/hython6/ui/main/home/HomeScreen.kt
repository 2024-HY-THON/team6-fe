package com.example.hython6.ui.main.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.hython6.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(appBarTitleSetter: (String) -> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var imageRes by remember { mutableStateOf(R.drawable.ham_default) }
    val coroutineScope = rememberCoroutineScope()
    var completedCount by remember { mutableStateOf(0) }
    var notCompletedCount by remember { mutableStateOf(0) }
    var category by remember { mutableStateOf("") }
    var contents by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("userId", "") ?: ""
    val serverUrl = stringResource(id = R.string.server_url)

    LaunchedEffect(Unit) {
        appBarTitleSetter("안해도 돼")

        try {
            // val url = URL("$serverUrl/category/main/$userId")
            // val connection = url.openConnection() as HttpURLConnection
            // connection.requestMethod = "GET"
            // connection.connect()

            // val responseCode = connection.responseCode
            // if (responseCode == HttpURLConnection.HTTP_OK) {
            //     val response = connection.inputStream.bufferedReader().use { it.readText() }
            //     val jsonObject = JSONObject(response)
            //     val habit = jsonObject.getJSONObject("habit")
            //     val count = jsonObject.getJSONObject("count")

            //     category = habit.getString("category")
            //     contents = habit.getString("contents")
            //     completedCount = count.getInt("completed_count")
            //     notCompletedCount = count.getInt("not_completed_count")
            // }

            // Dummy JSON data
            val dummyJson = """
                {
                    "habit": {
                        "category": "운동",
                        "contents": "푸시업 한 개 하기",
                        "habit_id": 6
                    },
                    "count": {
                        "completed_count": 2,
                        "not_completed_count": 1
                    }
                }
            """
            val jsonObject = JSONObject(dummyJson)
            val habit = jsonObject.getJSONObject("habit")
            val count = jsonObject.getJSONObject("count")

            category = habit.getString("category")
            contents = habit.getString("contents")
            completedCount = count.getInt("completed_count")
            notCompletedCount = count.getInt("not_completed_count")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF7F7F7),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .border(
                            width = 0.8.dp,
                            color = Color(0xFFDCDBDB),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .height(screenHeight * 0.15f)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "까짓거 할게",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "${completedCount}개!",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Divider(
                        color = Color(0xFFDCDBDB),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(0.8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "줏대있게 안 할게:",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "${notCompletedCount}개!",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(37.dp)
                            .background(
                                color = Color(0xFF5348E5),
                                shape = RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp,
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = category,
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 40.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.2f)
                            .background(
                                color = Color(0xFFFFFFFF),
                            )
                            .border(
                                width = 0.8.dp,
                                color = Color(0xFFDCDBDB),
                                shape = RoundedCornerShape(
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp,
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(contents)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF7F7F7),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .border(
                            width = 0.8.dp,
                            color = Color(0xFFDCDBDB),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .height(screenHeight * 0.1f)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .clickable {
                                coroutineScope.launch {
                                    imageRes = R.drawable.ham_good
                                    delay(1000)
                                    imageRes = R.drawable.ham_default
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "할게",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                    Divider(
                        color = Color(0xFFDCDBDB),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(0.8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .clickable {
                                coroutineScope.launch {
                                    imageRes = R.drawable.ham_good
                                    delay(1000)
                                    imageRes = R.drawable.ham_default
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "응안해",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Image(
                    painter = rememberImagePainter(data = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.25f)
                )
            }
        }
    }
}