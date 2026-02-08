package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app1.view.CalendarScreen
import com.example.app1.view.HomeScreen
import com.example.app1.view.SettingsScreen
import com.example.app1.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
        }
    }
}

const val ROUTE_HOME = "home"
const val ROUTE_CALENDAR = "calendar"
const val ROUTE_SETTINGS = "settings"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp() {
    val navController = rememberNavController()
    val sharedViewModel: TaskViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My ToDo App") },
                actions = {
                    IconButton(onClick = { navController.navigate(ROUTE_HOME) }) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = { navController.navigate(ROUTE_CALENDAR) }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Calendar")
                    }
                    IconButton(onClick = { navController.navigate(ROUTE_SETTINGS) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ROUTE_HOME,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(ROUTE_HOME) {
                HomeScreen(taskViewModel = sharedViewModel)
            }


            composable(ROUTE_CALENDAR) {
                CalendarScreen(taskViewModel = sharedViewModel)
            }


            composable(ROUTE_SETTINGS) {
                SettingsScreen()
            }
        }
    }
}