package com.example.notes.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.core.common.MultiViewModelFactory
import com.example.core.common.ViewModelKey
import com.example.feature.notes_list_ui.GetAllNotesUseCase
import com.example.feature.notes_list_ui.di.NotesListDeps
import com.example.feature.notes_list_ui.presentation.NotesListViewModel
import com.example.notes.GetAllNotesUseCaseImpl
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent: NotesListDeps {

    override val getAllNotesUseCase: GetAllNotesUseCase
    override val multiViewModelFactory: MultiViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}

@Module(includes = [AppBindsModule::class, ViewModelsModule::class])
class AppModule

@Module
interface AppBindsModule {
    @Binds
    fun bindsUseCase(getAllNotesUseCaseImpl: GetAllNotesUseCaseImpl): GetAllNotesUseCase
}

@Module
interface ViewModelsModule {
    @Binds
    @[IntoMap ViewModelKey(NotesListViewModel::class)]
    fun provideNotesListViewModel(notesListViewModel: NotesListViewModel): ViewModel
}