package com.example.app1.model

        import java.util.Date

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val dueDate: Date,
    val done: Boolean
)


val Tasks = listOf(
    Task(1, "Opiskele", "Tee tehtävä", 3, Date(), false),
    Task(2, "Käy kaupassa", "Kanaa ja riisiä", 2, Date(), true),
    Task(3, "Siivoa", "Imuroi olohuone", 1, Date(), false),
    Task(4, "Tee ruokaa", "Tee kanakastike", 2, Date(), false),
    Task(5, "Käy salilla", "Tee jalkatreeni", 2, Date(), false),
    Task(6, "Käy kirjastossa", "Palauta kirja", 1, Date(), false)
    )