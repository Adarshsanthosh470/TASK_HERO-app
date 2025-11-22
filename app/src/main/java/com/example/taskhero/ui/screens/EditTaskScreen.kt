package com.example.taskhero.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskhero.ui.theme.BlackBackground
import com.example.taskhero.ui.theme.Blue
import com.example.taskhero.ui.theme.LightText
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(navController: NavController, viewModel: TaskViewModel = hiltViewModel(), taskId: String?) {
    val taskUuid = try {
        UUID.fromString(taskId)
    } catch (e: Exception) {
        null
    }

    val task by taskUuid?.let { viewModel.getTask(it) }?.collectAsState() ?: remember { mutableStateOf(null) }

    var name by remember(task) { mutableStateOf(task?.name ?: "") }
    var description by remember(task) { mutableStateOf(task?.description ?: "") }
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog && task != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Task") },
            text = { Text("Are you sure you want to delete this task?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteTask(task!!)
                        navController.popBackStack()
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
                    Text("No")
                }
            }
        )
    }

    Scaffold(
        containerColor = BlackBackground,
        topBar = {
            TopAppBar(
                title = { Text("Edit Task", color = LightText) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BlackBackground),
                actions = {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Task", tint = LightText)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (task != null) {
                        viewModel.updateTask(task!!.id, name, description)
                        navController.popBackStack()
                    }
                },
                containerColor = Blue
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save Changes", tint = LightText)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            if (task != null) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Task Name") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(focusedTextColor = LightText, unfocusedTextColor = LightText)
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Task Description") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(focusedTextColor = LightText, unfocusedTextColor = LightText)
                )
            } else {
                Text("Task not found", color = LightText)
            }
        }
    }
}