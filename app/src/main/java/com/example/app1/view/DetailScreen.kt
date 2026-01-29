package com.example.app1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.example.app1.model.Task

@Composable
fun DetailDialog(task: Task, onDismiss: () -> Unit, onSave: (Task) -> Unit) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Muokkaa tehtävää") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(value = title, onValueChange = { title = it }, label = { Text("Otsikko") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Kuvaus") })
            }
        },
        confirmButton = {
            Button(onClick = { onSave(task.copy(title = title, description = description)) }) {
                Text("Tallenna")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Peruuta") }
        }
    )
}