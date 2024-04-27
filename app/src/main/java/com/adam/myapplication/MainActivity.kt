@file:OptIn(ExperimentalMaterial3Api::class)

package com.adam.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adam.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Number 1") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Number 2") }
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { result = calculateResult(number1, number2, '+') }) {
                Text("Add")
            }
            Button(onClick = { result = calculateResult(number1, number2, '-') }) {
                Text("Subtract")
            }
            Button(onClick = { result = calculateResult(number1, number2, '*') }) {
                Text("Multiply")
            }
            Button(onClick = { result = calculateResult(number1, number2, '/') }) {
                Text("Divide")
            }
        }

        result?.let {
            Text("Result: $it", modifier = Modifier.padding(top = 16.dp))
        }
    }
}

fun calculateResult(num1: String, num2: String, operator: Char): String {
    val number1 = num1.toDoubleOrNull()
    val number2 = num2.toDoubleOrNull()
    if (number1 == null || number2 == null) return "Invalid input"

    return when (operator) {
        '+' -> (number1 + number2).toString()
        '-' -> (number1 - number2).toString()
        '*' -> (number1 * number2).toString()
        '/' -> if (number2 != 0.0) (number1 / number2).toString() else "Cannot divide by zero"
        else -> "Unknown operation"
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    MyApplicationTheme {
        CalculatorApp()
    }
}
