package com.example.hython6.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(appBarTitleSetter: (String) -> Unit) {
    Scaffold {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray,
        ) {
            LaunchedEffect(Unit) {
                appBarTitleSetter("안해도 돼")
            }
        }
    }
}