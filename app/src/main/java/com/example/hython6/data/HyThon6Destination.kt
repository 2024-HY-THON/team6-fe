package com.example.hython6.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

enum class HyThon6Destination(
    val icons: DestinationIcons,
    val route: String,
    val label: String,
) {
    HOME(
        route = "home",
        icons = DestinationIcons(
            defaultIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
        ),
        label = "HOME",
    ),
    LIST(
        route = "list",
        icons = DestinationIcons(
            defaultIcon = Icons.Outlined.PlayArrow,
            selectedIcon = Icons.Filled.PlayArrow,
        ),
        label = "LIST",
    ),
    MY_PAGE(
        route = "my_page",
        icons = DestinationIcons(
            defaultIcon = Icons.Outlined.AccountCircle,
            selectedIcon = Icons.Filled.AccountCircle,
        ),
        label = "MY",
    ),
    ;
}

class DestinationIcons(
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
)
