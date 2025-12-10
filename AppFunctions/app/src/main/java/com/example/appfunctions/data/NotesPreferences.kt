package com.example.appfunctions.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val NOTES_SET_KEY = stringSetPreferencesKey("notes_set")
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "notes_prefs")

class NotesPreferences(private val context: Context) {
    val notesFlow: Flow<Set<Note>> = context.dataStore.data
        .map { preferences ->
            val stringSet = preferences[NOTES_SET_KEY] ?: emptySet()
            stringSet.map { contentString ->
                Note(contentString)
            }.toSet()
        }

    suspend fun saveNotes(notes: Set<Note>) {
        context.dataStore.edit { preferences ->
            val stringSet = notes.map { note ->
                note.content
            }.toSet()

            preferences[NOTES_SET_KEY] = stringSet
        }
    }
}
