package com.example.notes.di

import android.app.Application
import com.example.api.notes.domain.GetAllNotesUseCase
import com.example.core.common.MultiViewModelFactory
import com.example.feature.notes_list.di.NotesListDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : NotesListDeps {

    override val getAllNotesUseCase: GetAllNotesUseCase
    override val multiViewModelFactory: MultiViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}

@Module(includes = [AppBindsModule::class, ViewModelsModule::class, UseCasesModule::class])
class AppModule

@Module
interface AppBindsModule