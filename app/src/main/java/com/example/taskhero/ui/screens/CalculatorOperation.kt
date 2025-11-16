package com.example.taskhero.ui.screens

sealed class CalculatorOperation(val symbol: String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Multiply : CalculatorOperation("×")
    object Divide : CalculatorOperation("÷")
    object Percent : CalculatorOperation("%")
    object Negate : CalculatorOperation("+/-")
}