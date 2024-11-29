package com.example.hython6.ui.main.mypage

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationSetting() {
    val amPmOptions = listOf("AM", "PM")
    val hours = (1..12).toList()
    val minutes = (0..59).toList()

    var selectedAmPm by remember { mutableStateOf(amPmOptions[0]) }
    var selectedHour by remember { mutableStateOf(hours[0]) }
    var selectedMinute by remember { mutableStateOf(minutes[0]) }
    val context = LocalContext.current

    Scaffold(
        containerColor = Color.White
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TimeDial(
                        options = amPmOptions,
                        selectedOption = selectedAmPm,
                        onOptionSelected = { selectedAmPm = it }
                    )
                    TimeDial(
                        options = hours,
                        selectedOption = selectedHour,
                        onOptionSelected = { selectedHour = it }
                    )
                    TimeDial(
                        options = minutes,
                        selectedOption = selectedMinute,
                        onOptionSelected = { selectedMinute = it }
                    )
                }
                Button(onClick = {
                    Toast.makeText(context, "알림 시간이 설정되었습니다", Toast.LENGTH_SHORT).show()
//                  //TODO: Save the selected time to the database
                }) {
                    Text("확인")
                }
            }
        }
    }
}

@Composable
fun <T> TimeDial(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .width(60.dp)
            .height(150.dp)
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