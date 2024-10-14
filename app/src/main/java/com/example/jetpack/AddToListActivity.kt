package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AddToListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskList()
        }
    }

    @Composable
    fun TaskList() {
        var task by remember { mutableStateOf("") }
        val tasks = remember { mutableStateListOf<String>() }

        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            TextField(
                value = task,
                onValueChange = { task = it },
                label = { Text("Tarea") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                if (task.isNotEmpty()) {
                    tasks.add(task)
                    task = ""
                }
            }, modifier = Modifier.padding(top = 16.dp)) {
                Text("Agregar")
            }

            LazyColumn {
                items(tasks) { taskItem ->
                    Card(modifier = Modifier.padding(5.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(taskItem)
                        }
                    }
                }
            }
        }
    }
}
