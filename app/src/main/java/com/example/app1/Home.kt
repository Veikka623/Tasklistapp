package com.example.app1

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
import com.example.app1.viewmodel.TaskViewModel // TÄRKEÄ: Tämä korjaa Unresolved reference -virheen

@Composable
fun HomeScreen(taskViewModel: TaskViewModel = viewModel()) {
    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text("Tehtävälista", style = MaterialTheme.typography.headlineMedium)

        // Uuden tehtävän lisäys: TextField + Button
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

        // Suodatus ja järjestys
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { taskViewModel.sortByDueDate() }) { Text("Järjestä") }
            Button(onClick = { taskViewModel.filterByDone(true) }) { Text("Vain valmiit") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista komponenteilla: Checkbox, Teksti, Poista-painike
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(taskViewModel.tasks) { task ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
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