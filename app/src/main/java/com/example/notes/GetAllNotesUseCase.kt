package com.example.notes

import com.example.feature.notes_list_ui.GetAllNotesUseCase
import javax.inject.Inject

class GetAllNotesUseCaseImpl @Inject constructor(): GetAllNotesUseCase {
    override fun get(): String {
        return "GetAllNotesUseCaseImpl"
    }
}

