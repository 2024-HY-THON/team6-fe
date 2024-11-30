package com.example.hython6.ui.main.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.hython6.ui.theme.Gray2

@Composable
@Preview
fun AddCategoryCard(
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(160.dp)
            .fillMaxWidth()
            .clickable {

            }
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
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center),
                imageVector = Icons.Filled.Add,
                contentDescription = "DeleteIcon",
                tint = Color.Black
            )
        }

    }
}
