package com.example.taskhero.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskhero.data.Transaction
import com.example.taskhero.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CreditDetailScreen(personId: String?, viewModel: CreditViewModel, navController: NavController) {
    val personUuid = try {
        UUID.fromString(personId)
    } catch (e: Exception) {
        null
    }

    val person = personUuid?.let { viewModel.getPerson(it) }

    Scaffold(
        containerColor = BlackBackground,
        floatingActionButton = {
            if (person != null) {
                FloatingActionButton(
                    onClick = { navController.navigate("add_transaction/${person.id}") },
                    containerColor = Blue
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Transaction", tint = LightText)
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            if (person != null) {
                Text(
                    text = person.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = LightText
                )
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkBlue)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Total Balance", color = LightText)
                        Text(
                            text = "₹${String.format("%.2f", person.totalBalance)}",
                            color = if (person.totalBalance >= 0) Color.Green else Red,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(person.transactions) { transaction ->
                        TransactionRow(transaction = transaction)
                    }
                }
            } else {
                Text("Person not found", color = LightText)
            }
        }
    }
}

@Composable
fun TransactionRow(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkGrey)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = transaction.comment,
                    fontWeight = FontWeight.Bold,
                    color = LightText
                )
                Text(
                    text = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault()).format(transaction.date),
                    fontSize = 12.sp,
                    color = com.example.taskhero.ui.theme.LightGrey
                )
            }
            Text(
                text = "₹${String.format("%.2f", transaction.amount)}",
                color = if (transaction.amount >= 0) Color.Green else Red,
                fontWeight = FontWeight.Bold
            )
        }
    }
}