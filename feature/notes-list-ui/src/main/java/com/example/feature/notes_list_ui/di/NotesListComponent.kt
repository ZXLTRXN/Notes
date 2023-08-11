package com.example.feature.notes_list_ui.di

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.core.common.Feature
import com.example.feature.notes_list_ui.GetAllNotesUseCase
import com.example.feature.notes_list_ui.presentation.NotesListFragment
import dagger.Component
import kotlin.properties.Delegates.notNull

@[Feature Component(modules = [NotesListModule::class], dependencies = [NotesListDeps::class])]
internal interface NotesListComponent {

    fun inject(fragment: NotesListFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: NotesListDeps): Builder
        fun build(): NotesListComponent
    }
}

interface NotesListDeps {
    val getAllNotesUseCase: GetAllNotesUseCase
}

interface NotesListDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: NotesListDeps

    companion object : NotesListDepsProvider by NotesListDepsStore
}

object NotesListDepsStore : NotesListDepsProvider {

    override var deps: NotesListDeps by notNull()
}

internal class NotesListComponentViewModel : ViewModel() {
    val notesListComponent =
        DaggerNotesListComponent.builder().deps(NotesListDepsProvider.deps).build()
}