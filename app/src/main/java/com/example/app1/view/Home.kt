package com.example.app1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app1.model.Task
import com.example.app1.viewmodel.TaskViewModel

@Composable
fun HomeScreen(taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.tasks.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    if (showDialog) {
        DetailDialog(
            task = selectedTask,
            onDismiss = { showDialog = false },
            onSave = { title, desc ->
                if (selectedTask == null) {
                    taskViewModel.addTask(title, desc)
                } else {
                    taskViewModel.updateTask(selectedTask!!.copy(title = title, description = desc))
                }
                showDialog = false
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectedTask = null
                showDialog = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            selectedTask = task
                            showDialog = true
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(checked = task.done, onCheckedChange = { taskViewModel.toggleDone(task) })

                    Text(task.title, modifier = Modifier.weight(1f))


                    IconButton(onClick = { taskViewModel.removeTask(task) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}