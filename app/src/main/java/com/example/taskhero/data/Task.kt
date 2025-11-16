package com.example.taskhero.data

import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val isCompleted: Boolean = false
)