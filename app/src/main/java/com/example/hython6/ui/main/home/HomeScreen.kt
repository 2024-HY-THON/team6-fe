package com.example.hython6.ui.main.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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
import java.net.HttpURLConnection
import java.net.URL

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
    var habitId by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("userId", "") ?: ""
    val serverUrl = stringResource(id = R.string.server_url)

    LaunchedEffect(Unit) {
        appBarTitleSetter("안해도 돼")

        try {
            val url = URL("$serverUrl/category/random/habit/$userId")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val userToken = sharedPreferences.getString("userToken", "") ?: ""

            connection.setRequestProperty("Authorization", "Bearer $userToken")
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonObject = JSONObject(response)

                val randomHabit = jsonObject.getJSONObject("random_habit")
                category = jsonObject.getString("category")
                contents = randomHabit.getString("content")
                habitId = randomHabit.getInt("habit_id")
                Log.d("HomeScreen", "habitId: $habitId")
            }
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

                                    sendPostRequest(
                                        context,
                                        serverUrl,
                                        habitId,
                                        true
                                    ) { completed, notCompleted ->
                                        completedCount = completed
                                        notCompletedCount = notCompleted
                                        Log.d("HomeScreen", "completedCount: $completedCount")
                                        Log.d("HomeScreen", "notCompletedCount: $notCompletedCount")
                                    }
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

                                    sendPostRequest(
                                        context,
                                        serverUrl,
                                        habitId,
                                        false
                                    ) { completed, notCompleted ->
                                        completedCount = completed
                                        notCompletedCount = notCompleted
                                        Log.d("HomeScreen", "completedCount: $completedCount")
                                        Log.d("HomeScreen", "notCompletedCount: $notCompletedCount")
                                    }
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

fun sendPostRequest(
    context: Context,
    serverURL: String,
    habitId: Int,
    doOrNot: Boolean,
    onResponse: (completedCount: Int, notCompletedCount: Int) -> Unit,
) {
    try {
        val postUrl = URL("$serverURL/category/action/create/")
        val postConnection = postUrl.openConnection() as HttpURLConnection
        postConnection.requestMethod = "POST"
        postConnection.setRequestProperty("Content-Type", "application/json; utf-8")
        postConnection.setRequestProperty("Accept", "application/json")
        postConnection.doOutput = true

        val jsonInputString = JSONObject().apply {
            put("habit_id", habitId)
            put("do_or_not", doOrNot)
        }.toString()

        postConnection.outputStream.use { os ->
            val input = jsonInputString.toByteArray()
            os.write(input, 0, input.size)
        }

        val responseCode = postConnection.responseCode
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            val response = postConnection.inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(response)
            val completedCount = jsonObject.getInt("completed_count")
            val notCompletedCount = jsonObject.getInt("not_completed_count")
            Log.d("sendPostRequest", "completedCount: $completedCount, notCompletedCount: $notCompletedCount")
            onResponse(completedCount, notCompletedCount)
        } else {
            Log.e("sendPostRequest", "Failed to get a successful response: $responseCode")
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}