package com.example.hython6.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hython6.R
import com.example.hython6.ui.theme.HyThon6Theme
import org.json.JSONObject
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HyThon6Theme {
                LoginScreen()
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
    var userId by remember { mutableStateOf(sharedPreferences.getString("userId", "") ?: "") }
    var password by remember { mutableStateOf(sharedPreferences.getString("userPw", "") ?: "") }
    val serverUrl = stringResource(id = R.string.server_url)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "로그인") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24), contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    requestLogin(context, serverUrl, userId, password)
                    val intent = Intent(context, MainActivity::class.java).apply{
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                    with(sharedPreferences.edit()) {
                        putString("userId", userId)
                        putString("userPw", password)
                        apply()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "확인", fontSize = 16.sp)
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
                text = "아이디",
                style = TextStyle(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            TextField(
                value = userId,
                onValueChange = { userId = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next // 'Next'로 설정하여 다음 필드로 이동
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // 엔터를 눌렀을 때 키보드 숨기기
                        keyboardController?.hide()
                    }
                )
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done // 'Done'으로 설정하여 완료 시 키보드 숨기기
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
            )
        }
    }
}

fun requestLogin(context: Context, serverUrl: String, userId: String, password: String) {
    val policy = ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    val loginUrl = URL("$serverUrl/user/token/")
    val jsonInputString = JSONObject().apply {
        put("id", userId)
        put("password", password)
    }.toString()

    with(loginUrl.openConnection() as HttpURLConnection) {
        requestMethod = "POST"
        setRequestProperty("Content-Type", "application/json; utf-8")
        setRequestProperty("Accept", "application/json")
        doOutput = true

        outputStream.use { os: OutputStream ->
            val input = jsonInputString.toByteArray()
            os.write(input, 0, input.size)
        }

        inputStream.bufferedReader().use {
            val response = StringBuilder()
            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }
            println("Response: $response")

            val jsonResponse = JSONObject(response.toString())
            val accessToken = jsonResponse.getString("access")

            val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putString("userToken", accessToken)
                apply()
            }
        }
    }
}