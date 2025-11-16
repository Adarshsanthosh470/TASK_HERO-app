package com.example.taskhero.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Calculator : BottomNavItem(
        route = "calculator",
        title = "Calculator",
        icon = Icons.Filled.Calculate
    )

    object TaskList : BottomNavItem(
        route = "task_list",
        title = "To-Do List",
        icon = Icons.Filled.Checklist
    )

    object CreditBook : BottomNavItem(
        route = "credit_book",
        title = "Credit Book",
        icon = Icons.Filled.Favorite
    )
}