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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskhero.ui.theme.BlackBackground
import com.example.taskhero.ui.theme.Blue
import com.example.taskhero.ui.theme.LightText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditListScreen(navController: NavController, viewModel: CreditViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val totalBalance by viewModel.totalBalance.collectAsState()

    Scaffold(
        containerColor = BlackBackground,
        topBar = {
            TopAppBar(
                title = { Text("Credit Book", color = LightText) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BlackBackground)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_person") },
                containerColor = Blue
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Person", tint = LightText)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Text(text = "Total Balance: $totalBalance", color = LightText)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(uiState.people) { personWithTransactions ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("credit_detail/${personWithTransactions.person.id}") },
                        colors = CardDefaults.cardColors(containerColor = Blue)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = personWithTransactions.person.name, color = LightText)
                            Text(text = personWithTransactions.totalBalance.toString(), color = LightText)
                        }
                    }
                }
            }
        }
    }
}
