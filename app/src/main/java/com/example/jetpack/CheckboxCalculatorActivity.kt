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

class CheckboxCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorWithCheckbox()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CalculatorWithCheckbox() {
        var num1 by remember { mutableStateOf("") }
        var num2 by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }
        val operations = listOf("Suma", "Resta", "Multiplicación")
        val checkboxStates = remember { mutableStateMapOf<String, Boolean>().withDefault { false } }

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
                    Checkbox(
                        checked = checkboxStates.getValue(operation),
                        onCheckedChange = { checkboxStates[operation] = it }
                    )
                    Text(text = operation, modifier = Modifier.padding(start = 16.dp))
                }
            }

            Button(onClick = {
                val n1: Float? = num1.toFloatOrNull()
                val n2: Float? = num2.toFloatOrNull()
                if (n1 != null && n2 != null) {
                    var res = ""
                    if (checkboxStates["Suma"] == true) res += "$n1 + $n2 = ${n1 + n2}\n"
                    if (checkboxStates["Resta"] == true) res += "$n1 - $n2 = ${n1 - n2}\n"
                    if (checkboxStates["Multiplicación"] == true) res += "$n1 * $n2 = ${n1 * n2}\n"
                    result = if (res.isNotEmpty()) res else "Por favor, seleccione al menos una operación."
                } else {
                    result = "Por favor, ingrese números válidos."
                }
            }, modifier = Modifier.padding(top = 16.dp)) {
                Text("Calcular")
            }

            if (result.isNotEmpty()) {
                Text("Resultado:\n$result")
            }
        }
    }
}
