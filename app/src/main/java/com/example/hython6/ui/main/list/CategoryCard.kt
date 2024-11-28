package com.example.hython6.ui.main.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.hython6.ui.theme.Gray2
import com.example.hython6.ui.theme.Purple
import com.example.hython6.R

@Composable
fun CategoryCard(category: String, isEditing: Boolean) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(160.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray2,         // 카드 배경색
            contentColor = Color.Black      // 카드 내용의 텍스트 색상
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // 중앙에 텍스트 배치
            Text(
                text = category,
                modifier = Modifier
                    .align(Alignment.Center),
                style = TextStyle(fontSize = 16.sp, color = Color.Black),
            )

            // 오른쪽 상단에 이미지 배치
            IconButton(
                onClick = { /* TODO: 아이콘 버튼 클릭 시 동작 */ },
                modifier = Modifier
                    .size(35.dp)               // 아이콘 크기 설정
                    .align(Alignment.TopEnd)   // 오른쪽 상단에 배치
                    .padding(8.dp)             // 여백 설정
            ) {
                if (isEditing) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "DeleteIcon",
                        tint = Purple
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_alarm_off),
                        contentDescription = "AlarmOffIcon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewCategoryItem() {
    CategoryCard(category = "Category A", true)
}
