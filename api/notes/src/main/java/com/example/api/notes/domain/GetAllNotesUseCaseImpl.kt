package com.example.api.notes.domain

import com.example.api.notes.data.NotesRepository
import javax.inject.Inject

class GetAllNotesUseCaseImpl @Inject constructor(
    private val repo: NotesRepository
) : GetAllNotesUseCase {
    override fun get(): String {
        return repo.getAllNotes().toString()
    }
}

