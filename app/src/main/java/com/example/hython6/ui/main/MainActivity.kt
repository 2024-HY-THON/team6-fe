package com.example.hython6.ui.main

import MyPageScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hython6.data.HyThon6Destination
import com.example.hython6.ui.main.home.HomeScreen
import com.example.hython6.ui.main.list.ListScreen
import com.example.hython6.ui.theme.Blue
import com.example.hython6.ui.theme.HyThon6Theme
import com.example.hython6.ui.theme.Gray1
import com.example.hython6.ui.theme.White
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
        fetchFCMToken()
    }

    private fun fetchFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCM Token", "Token: $token")
            } else {
                Log.w("FCM Token", "Fetching FCM registration token failed", task.exception)
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    HyThon6Theme {
        val navController = rememberNavController()
        var currentDestination by remember { mutableStateOf(HyThon6Destination.HOME) }
        var appBarTitle by remember { mutableStateOf("") }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (currentRoute in listOf(
                        "home",
                        "my_page_screen",
                        "notification_setting",
                        "account_setting",
                        "how_to"
                    )
                ) {
                    TopAppBar(
                        title = {
                            Text(
                                text = appBarTitle,
                                color = Color(0xFF272727),
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight(700),
                                )
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = White,
                            titleContentColor = Color(0xFF272727),
                        )
                    )
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = White,
                ) {
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
                                    tint = if (isSelected) {
                                        Blue
                                    } else {
                                        Gray1
                                    },
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
                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(appBarTitleSetter = { appBarTitle = it }) }
                    composable("list") { ListScreen() }
                    composable("my_page_screen") {
                        MyPageScreen(appBarTitleSetter = {
                            appBarTitle = it
                        })
                    }
                }
                LaunchedEffect(currentDestination) {
                    when (currentDestination) {
                        HyThon6Destination.HOME -> navController.navigate("home")
                        HyThon6Destination.LIST -> navController.navigate("list")
                        HyThon6Destination.MY_PAGE -> navController.navigate("my_page_screen")
                    }
                }
            }
        }
    }
}