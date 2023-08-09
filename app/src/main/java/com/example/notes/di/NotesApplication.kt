package com.example.notes.di

import android.app.Application
import android.content.Context

class NotesApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is NotesApplication -> appComponent
        else -> this.applicationContext.appComponent
    }