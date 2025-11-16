package com.example.taskhero.ui.screens

import androidx.lifecycle.ViewModel
import com.example.taskhero.data.CreditPerson
import com.example.taskhero.data.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class CreditUiState(
    val people: List<CreditPerson> = emptyList()
)

class CreditViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CreditUiState())
    val uiState: StateFlow<CreditUiState> = _uiState.asStateFlow()

    fun addPerson(name: String, initialAmount: Double, comment: String) {
        val newTransaction = Transaction(amount = initialAmount, comment = comment)
        val newPerson = CreditPerson(name = name, transactions = listOf(newTransaction))
        _uiState.update { currentState ->
            currentState.copy(people = currentState.people + newPerson)
        }
    }

    fun addTransaction(personId: UUID, amount: Double, comment: String) {
        _uiState.update { currentState ->
            val updatedPeople = currentState.people.map {
                if (it.id == personId) {
                    val newTransaction = Transaction(amount = amount, comment = comment)
                    it.copy(transactions = it.transactions + newTransaction)
                } else {
                    it
                }
            }
            currentState.copy(people = updatedPeople)
        }
    }

    fun getPerson(personId: UUID): CreditPerson? {
        return _uiState.value.people.find { it.id == personId }
    }

    val totalBalance: Double
        get() = _uiState.value.people.sumOf { it.totalBalance }
}