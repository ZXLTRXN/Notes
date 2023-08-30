package com.example.notes.di

import android.app.Application
import android.content.Context
import com.example.feature.notes_list.di.NotesListDepsStore

class NotesApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        setDeps()
    }

    private fun setDeps() {
        NotesListDepsStore.deps = appComponent
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is NotesApplication -> appComponent
        else -> this.applicationContext.appComponent
    }