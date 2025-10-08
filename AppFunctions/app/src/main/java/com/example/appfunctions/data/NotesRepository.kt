package com.example.appfunctions.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepository @Inject constructor(
    @ApplicationContext context: Context) {
    private val notesPreferences = NotesPreferences(context)

    val notes: Flow<Set<String>> = notesPreferences.notesFlow

    suspend fun addNote(note: String) {
        val currentNotes = notes.first()
        // The '+' operator on a Set creates a new Set with the element added
        notesPreferences.saveNotes(currentNotes + note)
    }
}
