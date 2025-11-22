package com.example.taskhero.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction as RoomTransaction
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CreditDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: CreditPerson)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @RoomTransaction
    @Query("SELECT * FROM credit_people")
    fun getPeopleWithTransactions(): Flow<List<PersonWithTransactions>>

    @RoomTransaction
    @Query("SELECT * FROM credit_people WHERE id = :personId")
    fun getPersonWithTransactions(personId: UUID): Flow<PersonWithTransactions>
}
