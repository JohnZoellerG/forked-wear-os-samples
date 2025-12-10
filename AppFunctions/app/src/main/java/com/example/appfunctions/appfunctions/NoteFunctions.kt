package com.example.appfunctions.appfunctions

import androidx.appfunctions.AppFunctionContext
import androidx.appfunctions.AppFunctionSerializable
import androidx.appfunctions.service.AppFunction
import com.example.appfunctions.data.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteFunctions @Inject constructor(
    private val notesRepository: NotesRepository
) {
    /** The parameter to create the note */
    @AppFunctionSerializable(isDescribedByKdoc = true)
    data class AddNoteParams(
        /** The content of the note */
        val content: String
    )

    /* The user-created note. */
    @AppFunctionSerializable(isDescribedByKdoc = true)
    data class Note(
        /** the content of the note*/
        val content: String
    )

    /**
    * Creates a note based on [AddNoteParams]
     *
     * @param addNoteParams: The parameter to describe how to create a note.
     */
    @AppFunction(isDescribedByKdoc = true)
    suspend fun AddNote(
        appFunctionContext: AppFunctionContext,
        addNoteParams: AddNoteParams,
    ): Note = withContext(Dispatchers.IO) {
        notesRepository.addNote(addNoteParams.content)

        return@withContext notesRepository.getLastNote().toNote()
    }

    // I think this part is interesting... maybe an opportunity to provide a translation
    // function for a user
    private fun com.example.appfunctions.data.Note.toNote() = Note(content)
}
