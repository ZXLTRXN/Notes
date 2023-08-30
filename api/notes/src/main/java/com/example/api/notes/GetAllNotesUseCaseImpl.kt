package com.example.api.notes

import javax.inject.Inject

class GetAllNotesUseCaseImpl @Inject constructor() : GetAllNotesUseCase {
    override fun get(): String {
        return "GetAllNotesUseCaseImpl"
    }
}

