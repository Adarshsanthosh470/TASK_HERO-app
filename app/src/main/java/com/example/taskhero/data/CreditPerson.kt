package com.example.taskhero.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "credit_people")
data class CreditPerson(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String
)
