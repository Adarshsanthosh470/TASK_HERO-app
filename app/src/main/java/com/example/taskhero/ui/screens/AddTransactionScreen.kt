package com.example.taskhero.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.UUID

@Composable
fun AddTransactionScreen(
    navController: NavController,
    viewModel: CreditViewModel,
    personId: String?
) {
    var amount by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    val personUuid = try {
        UUID.fromString(personId)
    } catch (e: Exception) {
        null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount (use - for debit)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Comment") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val amountValue = amount.toDoubleOrNull()
                if (personUuid != null && amountValue != null) {
                    viewModel.addTransaction(personUuid, amountValue, comment)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Transaction")
        }
    }
}