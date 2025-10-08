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
    val notesFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[NOTES_SET_KEY] ?: emptySet()
        }

    suspend fun saveNotes(notes: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[NOTES_SET_KEY] = notes
        }
    }
}
