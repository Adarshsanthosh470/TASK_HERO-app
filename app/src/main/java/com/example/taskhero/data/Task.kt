package com.example.taskhero.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val isCompleted: Boolean = false
)
