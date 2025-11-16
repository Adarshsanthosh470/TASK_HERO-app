package com.example.taskhero.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskhero.ui.theme.*

@Composable
fun CalculatorScreen() {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state
    val buttonSpacing = 12.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBackground)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                reverseLayout = true
            ) {
                items(state.history.reversed()) { calculation ->
                    Text(
                        text = calculation,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth(),
                        color = LightGrey,
                        fontSize = 20.sp
                    )
                }
            }
            Text(
                text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = if (state.number1.length > 6 || state.number2.length > 6) 40.sp else 80.sp,
                color = LightText,
                maxLines = 2
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier
                        .background(LightGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Clear)
                    }
                )
                CalculatorButton(
                    symbol = "+/-",
                    modifier = Modifier
                        .background(LightGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Negate))
                    }
                )
                CalculatorButton(
                    symbol = "%",
                    modifier = Modifier
                        .background(LightGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Percent))
                    }
                )
                CalculatorButton(
                    symbol = "÷",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(7))
                    }
                )
                CalculatorButton(
                    symbol = "8",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(8))
                    }
                )
                CalculatorButton(
                    symbol = "9",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(9))
                    }
                )
                CalculatorButton(
                    symbol = "×",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(4))
                    }
                )
                CalculatorButton(
                    symbol = "5",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(5))
                    }
                )
                CalculatorButton(
                    symbol = "6",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(6))
                    }
                )
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(1))
                    }
                )
                CalculatorButton(
                    symbol = "2",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(2))
                    }
                )
                CalculatorButton(
                    symbol = "3",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(3))
                    }
                )
                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(0))
                    }
                )
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Decimal)
                    }
                )
                CalculatorButton(
                    symbol = "",
                    modifier = Modifier
                        .background(DarkGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    isIconButton = true,
                    onClick = {
                        viewModel.onAction(CalculatorAction.Delete)
                    }
                )
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier
                        .background(Blue)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Calculate)
                    }
                )
            }
        }
    }
}