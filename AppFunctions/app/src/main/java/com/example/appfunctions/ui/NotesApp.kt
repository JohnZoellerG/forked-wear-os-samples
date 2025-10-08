package com.example.appfunctions.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.appfunctions.ui.navigation.NavActions
import com.example.appfunctions.ui.navigation.NotesDestinations
import com.example.appfunctions.ui.screens.AddNoteScreen
import com.example.appfunctions.ui.screens.HomeScreen

@Composable
fun NotesApp() {
    AppScaffold {
        val navController = rememberSwipeDismissableNavController()
        val navActions = remember(navController) { NavActions(navController) }

        SwipeDismissableNavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen()
            }
            composable(route = NotesDestinations.ADD_NOTE_ROUTE) {
                AddNoteScreen()
            }
        }
    }
}
