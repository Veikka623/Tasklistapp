package com.example.app1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.app1.viewmodel.TaskViewModel
import java.text.SimpleDateFormat

@Composable
fun CalendarScreen(taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.tasks.collectAsState()

    val groupedTasks = tasks.groupBy {
        SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(it.dueDate)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Kalenteri", style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            groupedTasks.forEach { (date, tasksForDay) ->
                item {
                    Text(text = date, color = Color.Blue, modifier = Modifier.padding(top = 16.dp))
                    HorizontalDivider()
                }
                items(tasksForDay) { task ->
                    Text(
                        text = "• ${task.title}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}