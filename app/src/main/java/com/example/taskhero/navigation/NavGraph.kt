package com.example.taskhero.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskhero.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Calculator.route
    ) {
        composable(BottomNavItem.Calculator.route) {
            CalculatorScreen()
        }
        composable(BottomNavItem.TaskList.route) {
            TaskListScreen(navController = navController)
        }
        composable(BottomNavItem.CreditBook.route) {
            CreditBookScreen(navController = navController)
        }
        composable("add_task") {
            AddTaskScreen(navController = navController)
        }
        composable("edit_task/{taskId}") { backStackEntry ->
            EditTaskScreen(
                navController = navController,
                taskId = backStackEntry.arguments?.getString("taskId")
            )
        }
        composable("add_person") {
            AddPersonScreen(navController = navController)
        }
        composable("add_transaction/{personId}") { backStackEntry ->
            AddTransactionScreen(
                navController = navController,
                personId = backStackEntry.arguments?.getString("personId")
            )
        }
        composable("credit_detail/{personId}") { backStackEntry ->
            CreditDetailScreen(
                personId = backStackEntry.arguments?.getString("personId"),
                navController = navController
            )
        }
    }
}
