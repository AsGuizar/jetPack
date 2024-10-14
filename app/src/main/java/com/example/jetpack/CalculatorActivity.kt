package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class CalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calculator()
        }
    }

    @Composable
    fun Calculator() {
        var num1 by remember { mutableStateOf("") }
        var num2 by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }

        val operations = listOf("Suma", "Resta", "Multiplicación")
        var selectedOperation by remember { mutableStateOf(operations[0]) }

        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            TextField(
                value = num1,
                onValueChange = { num1 = it },
                label = { Text("Número 1") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = num2,
                onValueChange = { num2 = it },
                label = { Text("Número 2") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            operations.forEach { operation ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (operation == selectedOperation),
                        onClick = { selectedOperation = operation }
                    )
                    Text(text = operation, modifier = Modifier.padding(start = 16.dp))
                }
            }

            Button(onClick = {
                val n1: Float? = num1.toFloatOrNull()
                val n2: Float? = num2.toFloatOrNull()
                if (n1 != null && n2 != null) {
                    result = when (selectedOperation) {
                        "Suma" -> (n1 + n2).toString()
                        "Resta" -> (n1 - n2).toString()
                        "Multiplicación" -> (n1 * n2).toString()
                        else -> "Invalid operation"
                    }
                } else {
                    result = "Por favor, ingrese números válidos."
                }
            }, modifier = Modifier.padding(top = 16.dp)) {
                Text("Calcular")
            }

            if (result.isNotEmpty()) {
                Text("Resultado: $result")
            }
        }
    }
}
