package com.example.appfunctions.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.TextButton
import androidx.wear.compose.ui.tooling.preview.WearPreviewLargeRound
import com.example.appfunctions.ui.components.CenteredBoxPreview
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding


@Composable
fun NoteBodyButton() {

}

@Composable
fun AddNoteScreen() {
    val columnState = rememberTransformingLazyColumnState()

    ScreenScaffold {
        TransformingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = columnState,
            contentPadding = rememberResponsiveColumnPadding(),
        ) {
            item { NoteBodyButton() }
        }
    }
}

@WearPreviewLargeRound
@Composable
fun AddNoteScreenPreview() {
    AddNoteScreen()
}

