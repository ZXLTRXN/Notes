package com.example.feature.notes_list_ui.di

import androidx.lifecycle.ViewModel
import com.example.core.common.ViewModelKey
import com.example.feature.notes_list_ui.presentation.NotesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(includes = [NotesListBindsModule::class])
class NotesListModule

@Module
interface NotesListBindsModule {
//    @Binds
//    @[IntoMap ViewModelKey(NotesListViewModel::class)]
//    fun provideNotesListViewModel(notesListViewModel: NotesListViewModel): ViewModel
}