package com.example.feature.notes_list.di

import dagger.Module

@Module(includes = [NotesListBindsModule::class])
class NotesListModule

@Module
interface NotesListBindsModule {

}