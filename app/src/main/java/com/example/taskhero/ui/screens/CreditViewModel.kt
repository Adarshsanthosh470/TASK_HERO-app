package com.example.taskhero.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskhero.data.CreditDao
import com.example.taskhero.data.CreditPerson
import com.example.taskhero.data.PersonWithTransactions
import com.example.taskhero.data.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class CreditUiState(
    val people: List<PersonWithTransactions> = emptyList()
)

@HiltViewModel
class CreditViewModel @Inject constructor(private val creditDao: CreditDao) : ViewModel() {

    val uiState: StateFlow<CreditUiState> = creditDao.getPeopleWithTransactions().map { CreditUiState(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CreditUiState()
    )

    fun addPerson(name: String, initialAmount: Double, comment: String) {
        viewModelScope.launch {
            val newPerson = CreditPerson(name = name)
            creditDao.insertPerson(newPerson)
            val newTransaction = Transaction(personId = newPerson.id, amount = initialAmount, comment = comment)
            creditDao.insertTransaction(newTransaction)
        }
    }

    fun addTransaction(personId: UUID, amount: Double, comment: String) {
        viewModelScope.launch {
            val newTransaction = Transaction(personId = personId, amount = amount, comment = comment)
            creditDao.insertTransaction(newTransaction)
        }
    }

    fun getPersonWithTransactions(personId: UUID): Flow<PersonWithTransactions> {
        return creditDao.getPersonWithTransactions(personId)
    }

    val totalBalance: StateFlow<Double> = creditDao.getPeopleWithTransactions().map { people ->
        people.sumOf { personWithTransactions ->
            personWithTransactions.transactions.sumOf { it.amount }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )
}
