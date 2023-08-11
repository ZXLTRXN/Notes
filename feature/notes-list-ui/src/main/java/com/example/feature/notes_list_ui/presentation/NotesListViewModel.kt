package com.example.feature.notes_list_ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.notes_list_ui.GetAllNotesUseCase
import javax.inject.Inject
import javax.inject.Provider

internal class NotesListViewModel(private val getAllNotes: GetAllNotesUseCase) : ViewModel() {
    fun test() {

        println(getAllNotes.get())
    }

    class Factory @Inject constructor(
        private val getAllNotes: Provider<GetAllNotesUseCase>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == NotesListViewModel::class.java)
            return NotesListViewModel(getAllNotes.get()) as T
        }
    }
}