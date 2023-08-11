package com.example.notes.di

import com.example.feature.notes_list_ui.GetAllNotesUseCase
import com.example.feature.notes_list_ui.di.NotesListDeps
import com.example.notes.GetAllNotesUseCaseImpl
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@[AppScope Component(modules = [AppModule::class], dependencies = [AppDeps::class])]
interface AppComponent: NotesListDeps {

    override val getAllNotesUseCase: GetAllNotesUseCase

    @Component.Builder
    interface Builder {
        fun appDeps(deps: AppDeps): Builder
        fun build(): AppComponent
    }
}

@Module(includes = [AppBindsModule::class])
class AppModule

@Module
interface AppBindsModule {
    @Binds
    fun bindsUseCase(getAllNotesUseCaseImpl: GetAllNotesUseCaseImpl): GetAllNotesUseCase
}