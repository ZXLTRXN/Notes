package com.example.notes.di

import com.example.api.notes.GetAllNotesUseCase
import com.example.api.notes.GetAllNotesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {
    @Binds
    fun bindsUseCase(getAllNotesUseCaseImpl: GetAllNotesUseCaseImpl): GetAllNotesUseCase

}