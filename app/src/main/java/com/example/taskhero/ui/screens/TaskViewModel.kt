package com.example.taskhero.ui.screens

import androidx.lifecycle.ViewModel
import com.example.taskhero.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class TaskUiState(
    val tasks: List<Task> = emptyList()
)

class TaskViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    fun addTask(name: String, description: String) {
        val newTask = Task(name = name, description = description)
        _uiState.update { currentState ->
            currentState.copy(
                tasks = currentState.tasks + newTask
            )
        }
    }

    fun toggleTaskCompletion(taskId: UUID) {
        _uiState.update { currentState ->
            val updatedTasks = currentState.tasks.map { task ->
                if (task.id == taskId) {
                    task.copy(isCompleted = !task.isCompleted)
                } else {
                    task
                }
            }
            currentState.copy(tasks = updatedTasks)
        }
    }

    fun deleteTask(taskId: UUID) {
        _uiState.update { currentState ->
            currentState.copy(tasks = currentState.tasks.filterNot { it.id == taskId })
        }
    }

    fun updateTask(taskId: UUID, name: String, description: String) {
        _uiState.update { currentState ->
            val updatedTasks = currentState.tasks.map {
                if (it.id == taskId) {
                    it.copy(name = name, description = description)
                } else {
                    it
                }
            }
            currentState.copy(tasks = updatedTasks)
        }
    }

    fun getTask(taskId: UUID): Task? {
        return _uiState.value.tasks.find { it.id == taskId }
    }

    val activeTasksCount: Int
        get() = _uiState.value.tasks.count { !it.isCompleted }

    val completedTasksCount: Int
        get() = _uiState.value.tasks.count { it.isCompleted }
}