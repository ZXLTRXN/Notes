package com.example.notes.di

import androidx.lifecycle.ViewModel
import com.example.core.common.ViewModelKey
import com.example.feature.notes_list.presentation.NotesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {
    @Binds
    @[IntoMap ViewModelKey(NotesListViewModel::class)]
    fun provideNotesListViewModel(notesListViewModel: NotesListViewModel): ViewModel
}