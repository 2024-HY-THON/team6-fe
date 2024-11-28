package com.example.hython6.ui.main.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hython6.ui.theme.Gray2
import com.example.hython6.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen() {
    Scaffold (
        topBar = {
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ){
                // 검색바
                SearchBar()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    TopAppBar(
        modifier = Modifier
            .height(120.dp),
        title = {
            TextField(
                value = searchText,
                onValueChange = {searchText = it},
                placeholder = {
                    Text(
                        text = "검색어를 입력하세요",
                        style = TextStyle(fontSize = 15.sp)
                    )
                },
                modifier = Modifier
                    .width(355.dp)
                    .height(85.dp)
                    .fillMaxWidth()
                    .padding(
                        top = 30.dp,
                        end = 15.dp,
                    )
                    .background(White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Gray2, shape = RoundedCornerShape(8.dp)), // 검은색 테두리,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 15.sp),
            )
        },
    )
}