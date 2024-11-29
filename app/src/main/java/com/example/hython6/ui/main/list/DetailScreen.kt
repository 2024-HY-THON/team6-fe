package com.example.hython6.ui.main.list

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
                // 상단바
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
                // 구분선
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                        .background(Color.LightGray),
                )
                // 삭제 버튼
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { }
                        .padding(
                            top = 10.dp,
                            end = 20.dp
                        ),
                    text = "삭제",
                    style = TextStyle(fontSize = 18.sp)
                )
                // 세부 리스트
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 15.dp,     // 그리드 전체의 좌우 여백
                        vertical = 8.dp         // 그리드 전체의 상하 여백
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp) //아이템 간 상하 간격
                ) {
                    items(15 + 1) { index -> // 15는 예시로 고정된 개수, 실제 데이터로 수정 예정
                        if(index < 15)
                            RecommendationCard("recommend $index")
                        else
                            RecommendationCard(recommend = "+")
                    }
                }
            }
        }
    )
}