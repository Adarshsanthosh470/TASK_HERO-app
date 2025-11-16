package com.example.taskhero.data

import java.util.UUID

data class CreditPerson(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val transactions: List<Transaction> = emptyList()
) {
    val totalBalance: Double
        get() = transactions.sumOf { it.amount }
}