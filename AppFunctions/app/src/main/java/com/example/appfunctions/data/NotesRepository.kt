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

    val notes: Flow<Set<Note>> = notesPreferences.notesFlow

    suspend fun addNote(note: String) {
        val currentNotes = notes.first()
        notesPreferences.saveNotes(currentNotes + Note(note))
    }

    suspend fun getLastNote(): Note {
        val currentNotes =  notes.first()
        return Note(currentNotes.last().content)
    }
}
