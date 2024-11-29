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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HyThon6Theme {
                SignUpScreen()
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
    var userId by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val serverUrl = stringResource(id = R.string.server_url)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "회원가입") },
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
                    requestSignUp(context, serverUrl, userId, email, nickname, password)
                    userId = ""
                    email = ""
                    nickname = ""
                    password = ""
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    with(sharedPreferences.edit()) {
                        putString("userId", userId)
                        putString("email", email)
                        putString("nickname", nickname)
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
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "이메일",
                style = TextStyle(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth()
            )
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
        }
    }
}

fun requestSignUp(context: Context, serverUrl: String, userId: String, email: String, nickname: String, password: String) {
    val policy = ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    val signUpUrl = URL("$serverUrl/user/register/")
    val jsonInputString = JSONObject().apply {
        put("id", userId)
        put("password", password)
        put("email", email)
        put("nickname", nickname)
    }.toString()

    with(signUpUrl.openConnection() as HttpURLConnection) {
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