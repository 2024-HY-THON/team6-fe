package com.example.hython6.ui.main.mypage

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.hython6.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

data class Category(
    val categoryId: Int,
    val category: String,
    val choose: Boolean,
    val alarmTime: String?,
    val user: String,
    val randomHabit: Int?,
)

fun fetchCategories(
    context: Context,
    serverUrl: String,
    userId: String,
    onResult: (List<Category>) -> Unit,
) {
    val url = "$serverUrl/category/$userId/"
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonArray = JSONArray(response)
                val categories = mutableListOf<Category>()
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val category = Category(
                        categoryId = jsonObject.getInt("category_id"),
                        category = jsonObject.getString("category"),
                        choose = jsonObject.getBoolean("choose"),
                        alarmTime = jsonObject.optString("alarm_time"),
                        user = jsonObject.getString("user"),
                        randomHabit = jsonObject.optInt("random_habit")
                    )
                    categories.add(category)
                }
                withContext(Dispatchers.Main) {
                    onResult(categories)
                }
            } else {
                Log.e("fetchCategories", "Failed to fetch data: $responseCode")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("fetchCategories", "Exception: ${e.message}")
        }
    }
}

@Composable
fun NotificationSetting() {
    val context = LocalContext.current
    var categories by remember { mutableStateOf<List<Category>>(emptyList()) }
    val serverUrl = stringResource(id = R.string.server_url)

    LaunchedEffect(Unit) {
        fetchCategories(context, serverUrl, "test1") { fetchedCategories ->
            categories = fetchedCategories
        }
    }

    Scaffold(
        containerColor = Color.White
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(top = 15.dp),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                categories.forEach { category ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                var checked by remember { mutableStateOf(category.choose) }
                                Checkbox(
                                    checked = checked,
                                    onCheckedChange = { checked = it }
                                )
                                Text(
                                    text = category.category,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    ),
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                            Button(onClick = { /* 랜덤 버튼 클릭 시 동작 */ }) {
                                Text(text = category.alarmTime!!)
                            }
                        }
                    }

                }
            }
        }
    }

}

@Composable
fun <T> TimeDial(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .width(80.dp)
            .height(200.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(options) { option ->
            Text(
                text = option.toString(),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = if (option == selectedOption) FontWeight.Bold else FontWeight.Normal,
                    color = if (option == selectedOption) Color.Black else Color.Gray
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onOptionSelected(option) }
            )
        }
    }

}