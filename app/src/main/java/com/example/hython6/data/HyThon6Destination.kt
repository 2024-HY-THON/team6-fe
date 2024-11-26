package com.example.hython6.data

import com.example.hython6.R

enum class HyThon6Destination(
    val iconRes: Int,
    val route: String,
    val label: String,
) {
    HOME(
        route = "home",
        iconRes = R.drawable.ic_home,
        label = "HOME",
    ),
    LIST(
        route = "list",
        iconRes = R.drawable.ic_list,
        label = "LIST",
    ),
    MY_PAGE(
        route = "my_page",
        iconRes = R.drawable.ic_mypage,
        label = "MY",
    ),
    ;
}
