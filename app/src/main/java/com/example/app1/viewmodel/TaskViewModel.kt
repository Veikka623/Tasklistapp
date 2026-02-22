package com.example.app1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app1.data.repository.TaskRepository
import com.example.app1.model.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {


    val tasks: StateFlow<List<Task>> = repository.allTasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addTask(title: String, description: String) {
        val newTask = Task(
            title = title,
            description = description,
            priority = 1,
            dueDate = Date(),
            done = false
        )

        viewModelScope.launch {
            repository.insertTask(newTask)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
fun toggleDone(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task.copy(done = !task.done))
        }
    }

    fun removeTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}


class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}