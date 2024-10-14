package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.jetpack.ui.theme.JetPackTheme

class FruitCollectionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTheme {
                FruitCollectionScreen()
            }
        }
    }
}

data class Fruit(
    val name: String,
    val description: String,
    val imageUrl: String
)

@Composable
fun FruitCollectionScreen() {
    var fruitName by remember { mutableStateOf("") }
    var fruitDescription by remember { mutableStateOf("") }
    var fruitImageUrl by remember { mutableStateOf("") }
    var fruitList by remember { mutableStateOf(listOf<Fruit>()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Input fields for fruit details
                TextField(
                    value = fruitName,
                    onValueChange = { fruitName = it },
                    label = { Text("Fruit Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = fruitDescription,
                    onValueChange = { fruitDescription = it },
                    label = { Text("Fruit Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = fruitImageUrl,
                    onValueChange = { fruitImageUrl = it },
                    label = { Text("Image URL") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Button to add fruit to the list
                Button(
                    onClick = {
                        if (fruitName.isNotEmpty() && fruitDescription.isNotEmpty() && fruitImageUrl.isNotEmpty()) {
                            fruitList = fruitList + Fruit(fruitName, fruitDescription, fruitImageUrl)
                            fruitName = ""
                            fruitDescription = ""
                            fruitImageUrl = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Fruit")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Display fruit list
                if (fruitList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(fruitList.size) { index ->
                            val fruit = fruitList[index]
                            FruitItem(fruit)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun FruitItem(fruit: Fruit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Load the image from URL using Coil
            Image(
                painter = rememberAsyncImagePainter(fruit.imageUrl),
                contentDescription = fruit.name,
                contentScale = ContentScale.Fit, // Change here
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(text = fruit.name, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = fruit.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FruitCollectionPreview() {
    JetPackTheme {
        FruitCollectionScreen()
    }
}
