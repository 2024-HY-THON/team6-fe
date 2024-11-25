package com.example.hython6.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hython6.data.HyThon6Destination
import com.example.hython6.ui.main.home.HomeScreen
import com.example.hython6.ui.main.list.ListScreen
import com.example.hython6.ui.main.mypage.MyPageScreen
import com.example.hython6.ui.theme.HyThon6Theme

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
                BottomAppBar {
                    HyThon6Destination.values().forEach { destination ->
                        val isSelected = destination == currentDestination
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                currentDestination = destination
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) {
                                        destination.icons.selectedIcon
                                    } else {
                                        destination.icons.defaultIcon
                                    },
                                    contentDescription = destination.label,
                                )
                            },
                            label = {
                                Text(text = destination.label)
                            },
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