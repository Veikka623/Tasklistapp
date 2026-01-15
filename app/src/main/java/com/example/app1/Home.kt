package com.example.app1

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Date


import com.example.app1.domain.*

@Composable
fun HomeScreen() {

    val tasksState = remember { mutableStateOf(Tasks) }
    val showDialog = remember { mutableStateOf(false) }
    val newTaskTitle = remember { mutableStateOf("") }
    val newTaskDescription = remember { mutableStateOf("") }


    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Uusi tehtävä") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextField(
                        value = newTaskTitle.value,
                        onValueChange = { newTaskTitle.value = it },
                        label = { Text("Otsikko") }
                    )
                    TextField(
                        value = newTaskDescription.value,
                        onValueChange = { newTaskDescription.value = it },
                        label = { Text("Kuvaus") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (newTaskTitle.value.isNotBlank()) {
                        val uusi = Task(
                            id = (tasksState.value.maxOfOrNull { it.id } ?: 0) + 1,
                            title = newTaskTitle.value,
                            description = newTaskDescription.value,
                            priority = 1,
                            dueDate = Date(),
                            done = false
                        )

                        tasksState.value = addTask(tasksState.value, uusi)


                        newTaskTitle.value = ""
                        newTaskDescription.value = ""
                        showDialog.value = false
                    }
                }) { Text("Lisää") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) { Text("Peruuta") }
            }
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Tehtävälista", style = MaterialTheme.typography.headlineMedium)

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { showDialog.value = true }) {
                Text("Lisää")
            }


            Button(onClick = {
                tasksState.value = sortByDueDate(tasksState.value)
            }) {
                Text("Järjestä")
            }

            Button(onClick = {
                tasksState.value = filterByDone(tasksState.value, true)
            }) {
                Text("Vain valmiit")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tasksState.value) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = androidx.compose.ui.graphics.Color.Gray
                        )
                        Text(
                            text = if (task.done) "Tila: Valmis" else "Tila: Kesken",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Button(onClick = {

                        tasksState.value = toggleDone(tasksState.value, task.id)
                    }) {
                        Text("Muuta")
                    }
                }
            }
        }
    }
}