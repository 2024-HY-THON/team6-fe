package com.example.hython6.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hython6.R
import com.example.hython6.ui.theme.HyThon6Theme

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Preview
    @Composable
    fun MyApp() {
        HyThon6Theme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF5348E5))
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(0.3f))
                    Image(
                        painter = painterResource(id = R.drawable.splash_title),
                        contentDescription = null,
                        modifier = Modifier.width(261.dp).height(51.dp)
                    )
                    Spacer(modifier = Modifier.weight(0.4f))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(onClick = {
                            val intent = Intent(this@StartActivity, SignUpActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text(text = "회원가입", fontSize = 16.sp)
                        }
                        Button(onClick = {
                            val intent = Intent(this@StartActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text(text = "로그인", fontSize = 16.sp)
                        }
                    }
                    Spacer(modifier = Modifier.weight(0.3f))
                }
            }
        }
    }
}