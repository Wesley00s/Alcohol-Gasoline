package com.example.alcoholgasoline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alcoholgasoline.ui.theme.AlcoholGasolineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlcoholGasolineTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier) {
    var gasoline by remember { mutableStateOf("") }
    var alcohol by remember { mutableStateOf("") }

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Alcohol or Gasoline?",
            modifier = Modifier.padding(bottom = 32.dp),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )

        AnimatedVisibility(visible = gasoline.isNotBlank() && alcohol.isNotBlank()) {
            if (gasoline.isNotBlank() && alcohol.isNotBlank()) {
                val isGalosile = alcohol.toDouble() / gasoline.toDouble() > 0.7
                val value = if (isGalosile) { "Gasoline" } else { "Alcohol" }

                Text(
                    text = value,
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                    )
                )
            }
        }

        TextField(
            value = gasoline,
            onValueChange = {
                gasoline = it
            },
            label = {
                Text(
                    text = "Gasoline"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.padding(16.dp))
        TextField(
            value = alcohol,
            onValueChange = {
                alcohol = it
            },
            label = {
                Text(
                    text = "Alcohol"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AlcoholGasolineTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            App(modifier = Modifier.padding(innerPadding))
        }
    }
}