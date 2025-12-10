package com.example.appfunctions

import android.app.Application
import androidx.appfunctions.service.AppFunctionConfiguration
import com.example.appfunctions.appfunctions.NoteFunctions
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class NotesApplication : Application(), AppFunctionConfiguration.Provider {
    @Inject lateinit var noteFunctions: NoteFunctions

    override val appFunctionConfiguration: AppFunctionConfiguration
        get() =
            AppFunctionConfiguration.Builder()
                .addEnclosingClassFactory(NoteFunctions::class.java) { noteFunctions }
                .build()
}
