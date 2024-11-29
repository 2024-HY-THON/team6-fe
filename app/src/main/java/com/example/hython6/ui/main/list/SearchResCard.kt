package com.example.hython6.ui.main.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hython6.R
import com.example.hython6.ui.theme.Gray0
import com.example.hython6.ui.theme.White

@Composable
fun SearchResCard(searchRes: String, user: String) {
    Card(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,         // 카드 배경색
            contentColor = Color.Black      // 카드 내용의 텍스트 색상
        ),
        shape = RoundedCornerShape(0.dp),
    ){
        Row(
            modifier = Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .padding(start = 13.dp),
                text = searchRes,
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                modifier = Modifier
                    .height(18.dp)
                    .padding(start = 5.dp),
                text = "@" + user,
                fontSize = 12.sp,
                color = Gray0,
            )

            // 왼쪽 여백을 채워 Column을 오른쪽 끝에 배치
            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { /* TODO: 아이콘 버튼 클릭 시 동작  -> 저장 */ },
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_save),
                        contentDescription = "AlarmOffIcon",
                        modifier = Modifier
                            .size(18.dp)
                            .background(Color.White, shape = RoundedCornerShape(0.dp)),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "26",
                        fontSize = 8.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}