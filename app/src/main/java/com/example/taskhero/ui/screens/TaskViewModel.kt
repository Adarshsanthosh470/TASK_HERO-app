package com.example.taskhero.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskhero.data.Task
import com.example.taskhero.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class TaskUiState(
    val tasks: List<Task> = emptyList()
)

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskDao: TaskDao) : ViewModel() {

    val uiState: StateFlow<TaskUiState> = taskDao.getAllTasks().map { TaskUiState(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskUiState()
    )

    fun addTask(name: String, description: String) {
        viewModelScope.launch {
            taskDao.insertTask(Task(name = name, description = description))
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
        }
    }

    fun updateTask(taskId: UUID, name: String, description: String) {
        viewModelScope.launch {
            taskDao.updateTask(Task(id = taskId, name = name, description = description))
        }
    }

    fun getTask(taskId: UUID): StateFlow<Task> {
        return taskDao.getTaskById(taskId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Task(name = "", description = "")
        )
    }

    val activeTasksCount: StateFlow<Int> = taskDao.getAllTasks().map { tasks -> tasks.count { !it.isCompleted } }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    val completedTasksCount: StateFlow<Int> = taskDao.getAllTasks().map { tasks -> tasks.count { it.isCompleted } }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )
}
