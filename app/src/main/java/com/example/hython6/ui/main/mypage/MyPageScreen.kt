import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hython6.ui.main.mypage.AccountSetting
import com.example.hython6.ui.main.mypage.EditAccount
import com.example.hython6.ui.main.mypage.HowTo
import com.example.hython6.ui.main.mypage.NotificationSetting

@Suppress("NAME_SHADOWING")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPageScreen(appBarTitleSetter: (String) -> Unit) {
    val navController = rememberNavController()
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = Color.White
        ) {
            NavHost(navController, startDestination = "my_page") {
                composable("my_page") {
                    LaunchedEffect(Unit) {
                        appBarTitleSetter("마이페이지")
                    }
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
                                            "알림설정" -> {
                                                appBarTitleSetter("알림설정")
                                                navController.navigate("notification_setting")
                                            }

                                            "내 계정" -> {
                                                appBarTitleSetter("내 계정")
                                                navController.navigate("account_setting")
                                            }

                                            "사용 방법" -> {
                                                appBarTitleSetter("사용 방법")
                                                navController.navigate("how_to")
                                            }
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
                composable("account_setting") { AccountSetting(navController, appBarTitleSetter) }
                composable("edit_account") { EditAccount() }
                composable("how_to") { HowTo() }
            }
        }
    }
}