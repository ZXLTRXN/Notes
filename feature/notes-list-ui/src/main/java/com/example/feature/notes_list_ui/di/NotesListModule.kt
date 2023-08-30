package com.example.feature.notes_list_ui.di

import dagger.Module

@Module(includes = [NotesListBindsModule::class])
class NotesListModule

@Module
interface NotesListBindsModule {

}