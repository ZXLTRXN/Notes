package com.example.feature.notes_list.presentation

import androidx.lifecycle.ViewModel
import com.example.api.notes.domain.GetAllNotesUseCase
import javax.inject.Inject

class NotesListViewModel @Inject constructor(private val getAllNotes: GetAllNotesUseCase) :
    ViewModel() {
    fun test() {
        println(getAllNotes.get())
    }
}