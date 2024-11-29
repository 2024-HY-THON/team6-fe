package com.example.hython6.ui.main.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hython6.ui.theme.White

@Composable
fun RecommendationCard(recommend: String) {
    Card(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(15.dp))
            .padding(vertical = 4.dp, horizontal = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,         // 카드 배경색
            contentColor = Color.Black      // 카드 내용의 텍스트 색상

        ),
        shape = RoundedCornerShape(3.dp),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .padding(
                        start = 10.dp
                    ),
                text = "$recommend",
                style = TextStyle(fontSize = 18.sp)
            )


        }
    }
}