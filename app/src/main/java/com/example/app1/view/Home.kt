package com.example.app1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app1.viewmodel.TaskViewModel
import com.example.app1.model.Task

@Composable
fun HomeScreen(taskViewModel: TaskViewModel = viewModel()) {

    val tasks by taskViewModel.tasks.collectAsState()


    var editingTask by remember { mutableStateOf<Task?>(null) }
    var newTaskTitle by remember { mutableStateOf("") }


    editingTask?.let { task ->
        DetailDialog(
            task = task,
            onDismiss = { editingTask = null },
            onSave = { updatedTask ->
                taskViewModel.updateTask(updatedTask)
                editingTask = null
            }
        )
    }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text("Tehtävälista", style = MaterialTheme.typography.headlineMedium)

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text("Mitä tehdään?") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        taskViewModel.addTask(newTaskTitle, "Kuvaus")
                        newTaskTitle = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Lisää")
            }
        }


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { editingTask = task },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { taskViewModel.toggleDone(task.id) }
                    )
                    Text(
                        text = task.title,
                        modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = { taskViewModel.removeTask(task.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Poista")
                    }
                }
            }
        }
    }
}