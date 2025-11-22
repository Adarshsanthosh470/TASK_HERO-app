package com.example.taskhero.data

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithTransactions(
    @Embedded val person: CreditPerson,
    @Relation(
        parentColumn = "id",
        entityColumn = "personId"
    )
    val transactions: List<Transaction>
) {
    val totalBalance: Double
        get() = transactions.sumOf { it.amount }
}
