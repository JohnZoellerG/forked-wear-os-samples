package com.example.appfunctions.ui.navigation

import androidx.navigation.NavHostController

object NotesDestinations {
    const val ADD_NOTE_ROUTE = "add_note"
}

class NavActions(navController: NavHostController) {
    val navigateToAddNote: () -> Unit = {
        navController.navigate(NotesDestinations.ADD_NOTE_ROUTE)
    }
}
