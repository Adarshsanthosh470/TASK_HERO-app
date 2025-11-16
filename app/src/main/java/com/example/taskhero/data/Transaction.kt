package com.example.taskhero.data

import java.util.Date
import java.util.UUID

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val amount: Double,
    val comment: String,
    val date: Date = Date()
)