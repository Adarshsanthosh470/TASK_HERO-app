package com.example.taskhero.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskhero.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController) {
    val taskViewModel: TaskViewModel = viewModel()
    val creditViewModel: CreditViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Calculator.route
    ) {
        composable(BottomNavItem.Calculator.route) {
            CalculatorScreen()
        }
        composable(BottomNavItem.TaskList.route) {
            TaskListScreen(navController = navController, viewModel = taskViewModel)
        }
        composable(BottomNavItem.CreditBook.route) {
            CreditBookScreen(navController = navController, viewModel = creditViewModel)
        }
        composable("add_task") {
            AddTaskScreen(navController = navController, viewModel = taskViewModel)
        }
        composable("edit_task/{taskId}") { backStackEntry ->
            EditTaskScreen(
                navController = navController,
                viewModel = taskViewModel,
                taskId = backStackEntry.arguments?.getString("taskId")
            )
        }
        composable("add_person") {
            AddPersonScreen(navController = navController, viewModel = creditViewModel)
        }
        composable("add_transaction/{personId}") { backStackEntry ->
            AddTransactionScreen(
                navController = navController,
                viewModel = creditViewModel,
                personId = backStackEntry.arguments?.getString("personId")
            )
        }
        composable("credit_detail/{personId}") { backStackEntry ->
            CreditDetailScreen(
                personId = backStackEntry.arguments?.getString("personId"),
                viewModel = creditViewModel,
                navController = navController
            )
        }
    }
}