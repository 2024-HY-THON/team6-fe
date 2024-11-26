package com.example.hython6.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hython6.data.HyThon6Destination
import com.example.hython6.ui.main.home.HomeScreen
import com.example.hython6.ui.main.list.ListScreen
import com.example.hython6.ui.main.mypage.MyPageScreen
import com.example.hython6.ui.theme.Blue
import com.example.hython6.ui.theme.HyThon6Theme
import com.example.hython6.ui.theme.Gray1
import com.example.hython6.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun App() {
    HyThon6Theme {
        var currentDestination by remember { mutableStateOf(HyThon6Destination.HOME) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomAppBar (
                    containerColor = White,
                ){
                    HyThon6Destination.values().forEach { destination ->
                        val isSelected = destination == currentDestination
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                currentDestination = destination
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = destination.iconRes),
                                    tint = if(isSelected) {
                                        Blue
                                    } else { Gray1 },
                                    contentDescription = destination.label,
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(indicatorColor = White)
                        )
                    }
                }
            },
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                when (currentDestination) {
                    HyThon6Destination.HOME -> HomeScreen()
                    HyThon6Destination.LIST -> ListScreen()
                    HyThon6Destination.MY_PAGE -> MyPageScreen()
                }
            }
        }
    }
}