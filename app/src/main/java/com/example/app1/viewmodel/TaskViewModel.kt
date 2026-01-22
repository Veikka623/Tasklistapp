package com.example.app1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.app1.domain.Task
import com.example.app1.domain.Tasks
import java.util.Date

class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf(listOf<Task>())
        private set


    init {
        tasks = Tasks
    }

    fun addTask(title: String, description: String) {
        val newTask = Task(
            id = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            description = description,
            priority = 1,
            dueDate = Date(),
            done = false
        )
        tasks = tasks + newTask
    }

    fun toggleDone(id: Int) {
        tasks = tasks.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(id: Int) {
        tasks = tasks.filter { it.id != id }
    }

    fun filterByDone(done: Boolean) {
        tasks = tasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }
}