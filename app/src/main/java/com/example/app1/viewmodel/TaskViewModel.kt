package com.example.app1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app1.model.Task
import com.example.app1.model.Tasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow(Tasks)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(title: String, description: String) {
        val newTask = Task(
            id = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1,
            title = title,
            description = description,
            priority = 1,
            dueDate = java.util.Date(),
            done = false
        )
        _tasks.value += newTask
    }


    fun updateTask(updatedTask: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == updatedTask.id) updatedTask else it
        }
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == id) it.copy(done = !it.done) else it
        }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }
}