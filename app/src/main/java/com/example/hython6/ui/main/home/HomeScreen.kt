package com.example.hython6.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray,
        ){
            Text(text = "홈페이지")
        }
    }
}