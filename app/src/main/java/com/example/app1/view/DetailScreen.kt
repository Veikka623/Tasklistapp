package com.example.app1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.app1.model.Task
import java.util.Date

@Composable
fun DetailDialog(
    task: Task?,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (task == null) "Lisää" else "Muokkaa") },
        text = {
            Column {
                TextField(value = title, onValueChange = { title = it }, label = { Text("Otsikko") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Kuvaus") })
            }
        },
        confirmButton = {
            Button(onClick = { onSave(title, description) }) {
                Text("Tallenna")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Peruuta") }
        }
    )
}