package com.example.appfunctions.ui.screens

import android.app.RemoteInput
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.TransformingLazyColumnState
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.RevealValue
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SwipeToReveal
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.rememberRevealState
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound
import androidx.wear.input.RemoteInputIntentHelper
import com.example.appfunctions.data.Note
import com.example.appfunctions.ui.components.*
import com.example.appfunctions.ui.viewmodel.NotesViewModel
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding
import kotlinx.coroutines.launch

@Composable
fun NotesListHeader() {
    ListHeader(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Notes List")
    }
}

@Composable
fun NoteButton(columnState: TransformingLazyColumnState, noteContent: Note) {
    val revealState = rememberRevealState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(columnState.isScrollInProgress) {
        if (columnState.isScrollInProgress && revealState.currentValue != RevealValue.Covered) {
            coroutineScope.launch {
                revealState.animateTo(targetValue = RevealValue.Covered)
            }
        }
    }

    SwipeToReveal(
        primaryAction = {
            PrimaryActionButton(
                onClick = {},
                icon = {},
                text = {},
            )
        },
        onSwipePrimaryAction = {},
        revealState = revealState,
    ) {
        Button(
            onClick = {
                // navigate to view note
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = noteContent.content, maxLines = 1)
        }
    }
}

@Composable
fun AddNoteButton(onNoteAdded: (String) -> Unit) {
    val inputLabel = "user_input"
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        result.data?.let { data ->
            val results = RemoteInput.getResultsFromIntent(data)
            val newText = results.getCharSequence(inputLabel)?.toString()
            if (!newText.isNullOrEmpty()) {
                onNoteAdded(newText)
            }
        }
    }

    Button(
        onClick = {
            val intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
            val remoteInputs: List<RemoteInput> = listOf(
                RemoteInput.Builder(inputLabel).setLabel("New Note").build()
            )
            RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)
            launcher.launch(intent)
        },
    ) {
        Text("Add Note")
    }
}

@Composable
fun HomeScreen(notesViewModel: NotesViewModel = hiltViewModel()) {
    val columnState = rememberTransformingLazyColumnState()
    val notes by notesViewModel.notes.collectAsStateWithLifecycle()

    ScreenScaffold {
        TransformingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = columnState,
            contentPadding = rememberResponsiveColumnPadding(),
        ) {
            item { NotesListHeader() }
            item { AddNoteButton(onNoteAdded = { newNote -> notesViewModel.addNote(newNote) }) }
            items(notes.toList()) { note ->
                NoteButton(columnState, note)
            }
        }
    }
}

@WearPreviewLargeRound
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@WearPreviewLargeRound
@Composable
fun NotesListPreview() {
    NotesListHeader()
}

@WearPreviewLargeRound
@Composable
fun NoteButtonPreview() {
    CenteredBoxPreview {
        NoteButton(rememberTransformingLazyColumnState(), Note("john"))
    }
}

@WearPreviewLargeRound
@Composable
fun AddNoteButtonPreview() {
    CenteredBoxPreview {
        AddNoteButton({})
    }
}
