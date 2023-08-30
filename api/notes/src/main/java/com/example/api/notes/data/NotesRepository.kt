package com.example.api.notes.data

import javax.inject.Inject

interface NotesRepository {
    fun getAllNotes(): List<String>
}


internal class NotesRepositoryImpl @Inject constructor(): NotesRepository {
    override fun getAllNotes(): List<String> {
        return listOf("notes from repo")
    }

}