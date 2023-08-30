package com.example.api.notes.di

import com.example.api.notes.data.NotesRepository
import com.example.api.notes.data.NotesRepositoryImpl
import dagger.Binds
import dagger.Module


@Module(includes = [ApiNotesBindsModule::class])
internal class ApiNotesModule

@Module
internal interface ApiNotesBindsModule {
    @Binds
    fun bindsNotesRepo(notesRepo: NotesRepositoryImpl): NotesRepository
}