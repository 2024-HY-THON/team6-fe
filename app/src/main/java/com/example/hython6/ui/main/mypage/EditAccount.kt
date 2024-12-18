package com.example.hython6.ui.main.mypage

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hython6.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditAccount() {
    var userId by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val serverUrl = stringResource(id = R.string.server_url)

    LaunchedEffect(Unit) {
        getUserDetails(context,serverUrl) { id, nick ->
            userId = id
            nickname = nick
        }
    }

    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    // Handle save action
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "저장", fontSize = 16.sp)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "닉네임",
                style = TextStyle(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            TextField(
                value = nickname,
                onValueChange = { nickname = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "아이디",
                style = TextStyle(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            TextField(
                value = userId,
                onValueChange = { userId = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "비밀번호",
                style = TextStyle(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

suspend fun getUserDetails(context: Context, serverUrl: String, onResult: (String, String) -> Unit) {
    withContext(Dispatchers.IO) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val userToken = sharedPreferences.getString("userToken", "") ?: return@withContext

        val url = URL("$serverUrl/user/detail")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Authorization", "Bearer $userToken")

        try {
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonResponse = JSONObject(response)
                val id = jsonResponse.getString("id")
                val nickname = jsonResponse.getString("nickname")
                withContext(Dispatchers.Main) {
                    onResult(id, nickname)
                }
            }
        } finally {
            connection.disconnect()
        }
    }
}