package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class NumSumActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Suma()
        }
    }

    @Composable
    fun Suma() {
        val num1 = remember { mutableStateOf("") }
        val num2 = remember { mutableStateOf("") }
        val resultado = remember { mutableStateOf("0") }

        fun sumar() {
            val num1Int = num1.value.toIntOrNull() ?: 0
            val num2Int = num2.value.toIntOrNull() ?: 0
            resultado.value = "$num1Int + $num2Int = ${num1Int + num2Int}"
        }

        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()) {
            TextField(
                value = num1.value,
                onValueChange = { num1.value = it },
                label = { Text("Número 1") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = num2.value,
                onValueChange = { num2.value = it },
                label = { Text("Número 2") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { sumar() }) {
                Text("Sumar")
            }
            Text("Resultado: ${resultado.value}")
        }
    }
}
