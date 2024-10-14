package com.example.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge UI if needed
        setContent {
            JetPackTheme {
                MainMenu { activityClass ->
                    navigateToActivity(activityClass)
                }
            }
        }
    }

    // Function to navigate to an activity
    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}

// Move MainMenu function outside the MainActivity class
@Composable
fun MainMenu(navigateToActivity: (Class<*>) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                // Buttons for each activity
                Button(onClick = { navigateToActivity(CalculatorActivity::class.java) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Calculator with Radio Buttons")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { navigateToActivity(CheckboxCalculatorActivity::class.java) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Basic Operations with Checkbox")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { navigateToActivity(AddToListActivity::class.java) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Add Items to List")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { navigateToActivity(NumSumActivity::class.java) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Sum of Two Numbers")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { navigateToActivity(FruitCollectionActivity::class.java) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Fruit Collection")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    JetPackTheme {
        MainMenu { _ -> }
    }
}
