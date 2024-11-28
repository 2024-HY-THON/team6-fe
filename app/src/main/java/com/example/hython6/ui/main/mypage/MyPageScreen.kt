import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hython6.ui.main.mypage.AccountSetting
import com.example.hython6.ui.main.mypage.HowTo
import com.example.hython6.ui.main.mypage.NotificationSetting

@Suppress("NAME_SHADOWING")
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPageScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 2.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    ),
                title = {
                    Text(
                        text = "마이페이지",
                        color = Color(0xFF272727),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(700),
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF272727),
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.White
        ) {
            NavHost(navController, startDestination = "my_page") {
                composable("my_page") {
                    val items = listOf("알림설정", "내 계정", "사용 방법")
                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(items) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(59.dp)
                                    .clickable {
                                        when (item) {
                                            "알림설정" -> navController.navigate("notification_setting")
                                            "내 계정" -> navController.navigate("account_setting")
                                            "사용 방법" -> navController.navigate("how_to")
                                        }
                                    }
                                    .drawBehind {
                                        val strokeWidth = 1.dp.toPx()
                                        val y = size.height - strokeWidth / 2
                                        drawLine(
                                            color = Color(0xFFF4F4F4),
                                            start = Offset(0f, y),
                                            end = Offset(size.width, y),
                                            strokeWidth = strokeWidth
                                        )
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
                composable("notification_setting") { NotificationSetting() }
                composable("account_setting") { AccountSetting() }
                composable("how_to") { HowTo() }
            }
        }
    }
}