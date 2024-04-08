package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {

    var showTask by rememberSaveable { mutableStateOf(false) }
    var count by rememberSaveable { mutableStateOf(0) }
    var enabled by remember { mutableStateOf(true) }

    Column(modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        if (count > 0) {
            Text(text = "You´ve had $count glasses.")
            if (showTask) {
                WellnessTaskItem(
                    taskName = "Have you taken your 15 minutes walk today ?",
                    false,
                    {},
                    onClose = { showTask = false })
            }
        }

        Row(modifier = modifier.padding(16.dp)) {
            Button(
                content = { Text(text = "Add") },
                shape = RectangleShape,
                onClick = {
                    if (count > 0) showTask = true
                    if (count < 10 && enabled) count++ else enabled = false
                },
                modifier = Modifier.padding(8.dp),
                enabled = enabled
            )
            Button(
                content = { Text(text = "Clear water count") },
                shape = RectangleShape,
                onClick = { count = 0 },
                modifier = Modifier.padding(8.dp),
            )
        }
    }


}