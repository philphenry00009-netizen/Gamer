package com.example.quickfocus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FocusTimerScreen()
                }
            }
        }
    }
}

@Composable
fun FocusTimerScreen() {
    var timeLeft by remember { mutableStateOf(25 * 60) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            isRunning = false
        }
    }

    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(text = "${timeLeft / 60}:${(timeLeft % 60).toString().padStart(2, '0')}", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(16.dp))
        Button(onClick = { isRunning = !isRunning }) {
            Text(if (isRunning) "Pause" else "Start Focus")
        }
    }
}
