package com.example.hython6.ui.main.list

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hython6.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(categoryName: String?) {
    Scaffold(
        topBar = { },
        content = {
            Column(){
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .padding(start = 20.dp)
                            .align(alignment = Alignment.CenterStart),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "$categoryName",
                            fontSize = 20.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF272727),
                        )
                        IconButton(
                            onClick = { /* TODO: 아이콘 버튼 클릭 시 동작 */ },
                            modifier = Modifier
                                .size(35.dp)
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "AlarmOffIcon",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = { /* TODO: 아이콘 버튼 클릭 시 동작 */ },
                            modifier = Modifier
                                .padding(end = 20.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_download),
                                contentDescription = "AlarmOffIcon",
                                modifier = Modifier.size(35.dp)
                            )
                        }

                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                        .background(Color.LightGray),
                )
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Selected Category: categoryName",
                    style = TextStyle(fontSize = 20.sp)
                )
            }
        }
    )
}