package com.example.appfunctions.ui.extensions

import android.app.RemoteInput
import android.content.Intent
import android.view.inputmethod.EditorInfo
import androidx.activity.result.ActivityResultLauncher
import androidx.wear.input.RemoteInputIntentHelper
import androidx.wear.input.wearableExtender

/**
 * Creates and launches the system's text input intent.
 */
private fun launchTextInput(launcher: ActivityResultLauncher<Intent>, inputLabel: String) {
    val intent = RemoteInputIntentHelper.createActionRemoteInputIntent()
    val remoteInputs: List<RemoteInput> = listOf(
        RemoteInput.Builder(inputLabel)
            .setLabel("New Note")
            .wearableExtender {
                setInputActionType(EditorInfo.IME_ACTION_DONE)
            }.build()
    )
    RemoteInputIntentHelper.putRemoteInputsExtra(intent, remoteInputs)
    launcher.launch(intent)
}
