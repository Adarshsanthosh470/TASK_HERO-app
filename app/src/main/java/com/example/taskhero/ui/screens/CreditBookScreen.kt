package com.example.taskhero.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskhero.data.PersonWithTransactions
import com.example.taskhero.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditBookScreen(navController: NavController, viewModel: CreditViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val totalBalance by viewModel.totalBalance.collectAsState()

    Scaffold(
        containerColor = BlackBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_person") },
                containerColor = Blue
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Credit/Debit", tint = LightText)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Text(
                text = "Credits & Debits",
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
                        text = "₹${String.format("%.2f", totalBalance)}",
                        color = if (totalBalance >= 0) Color.Green else Red,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(uiState.people) { personWithTransactions ->
                    CreditPersonRow(personWithTransactions = personWithTransactions, navController = navController)
                }
            }
        }
    }
}

@Composable
fun CreditPersonRow(personWithTransactions: PersonWithTransactions, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("credit_detail/${personWithTransactions.person.id}") },
        colors = CardDefaults.cardColors(containerColor = DarkGrey)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = personWithTransactions.person.name, fontWeight = FontWeight.Bold, color = LightText)
            Text(
                text = "₹${String.format("%.2f", personWithTransactions.totalBalance)}",
                color = if (personWithTransactions.totalBalance >= 0) Color.Green else Red,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
