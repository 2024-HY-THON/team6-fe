package com.example.hython6.ui.main.mypage

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

import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun NotificationSetting() {
    val amPmOptions = listOf("오전", "오후")
    val hours = (1..12).toList()
    val minutes = (0..59).toList()

    var selectedAmPm by remember { mutableStateOf(amPmOptions[0]) }
    var selectedHour by remember { mutableStateOf(hours[0]) }
    var selectedMinute by remember { mutableStateOf(minutes[0]) }
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)  // contentPadding 사용
                .padding(top = 15.dp),
            color = Color.White,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
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
                        Button(onClick = { showDialog = true }) {
                            Text(text = "랜덤")
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(dismissOnClickOutside = true)
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
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
                        showDialog = false
                        val selectedTime = "${selectedAmPm} ${selectedHour}시 ${selectedMinute}분"
                        Toast.makeText(context, "알림 시간: $selectedTime", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("확인")
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