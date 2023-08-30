package com.example.feature.notes_list_ui.presentation

import androidx.lifecycle.ViewModel
import com.example.feature.notes_list_ui.GetAllNotesUseCase
import javax.inject.Inject

class NotesListViewModel @Inject constructor(private val getAllNotes: GetAllNotesUseCase) :
    ViewModel() {
    fun test() {
        println(getAllNotes.get())
    }
}