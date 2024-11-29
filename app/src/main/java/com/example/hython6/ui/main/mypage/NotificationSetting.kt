package com.example.hython6.ui.main.mypage

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun NotificationSetting() {
    Scaffold(
        containerColor = Color.White,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "랜덤은 아침, 점심, 저녁 중으로 가장 적합한 때에 랜덤으로 발송돼요 :)",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF8E8E8E),
                    )
                )
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE8E8E8),
                            shape = RoundedCornerShape(size = 10.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
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
                            var checked by remember { mutableStateOf(false) }
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { checked = it }
                            )
                            Text(
                                text = "운동",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                ),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Button(onClick = { /* TODO: Add your action here */ }) {
                            Text(text = "랜덤")
                        }
                    }
                }
            }
        }
    }
}