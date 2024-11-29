package com.example.hython6.ui.main.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.hython6.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(appBarTitleSetter: (String) -> Unit) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var imageRes by remember { mutableStateOf(R.drawable.ham_default) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.White,
        ) {
            LaunchedEffect(Unit) {
                appBarTitleSetter("안해도 돼")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF7F7F7),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .border(
                            width = 0.8.dp,
                            color = Color(0xFFDCDBDB),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .height(screenHeight * 0.15f)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "까짓거 할게",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "0개!",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Divider(
                        color = Color(0xFFDCDBDB),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(0.8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "줏대있게 안 할게:",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "0개!",
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(37.dp)
                            .background(
                                color = Color(0xFF5348E5),
                                shape = RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp,
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "건강 관리",
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 40.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.2f)
                            .background(
                                color = Color(0xFFFFFFFF),
                            )
                            .border(
                                width = 0.8.dp,
                                color = Color(0xFFDCDBDB),
                                shape = RoundedCornerShape(
                                    bottomStart = 20.dp,
                                    bottomEnd = 20.dp,
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("건강 관리 내용")
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFF7F7F7),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .border(
                            width = 0.8.dp,
                            color = Color(0xFFDCDBDB),
                            shape = RoundedCornerShape(size = 20.dp)
                        )
                        .height(screenHeight * 0.1f)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .clickable {
                                coroutineScope.launch {
                                    imageRes = R.drawable.ham_good
                                    delay(1000)
                                    imageRes = R.drawable.ham_default
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "할게",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                    Divider(
                        color = Color(0xFFDCDBDB),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(0.8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .clickable {
                                coroutineScope.launch {
                                    imageRes = R.drawable.ham_good
                                    delay(1000)
                                    imageRes = R.drawable.ham_default
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "응안해",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Image(
                    painter = rememberImagePainter(data = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.25f)
                )
            }
        }
    }
}