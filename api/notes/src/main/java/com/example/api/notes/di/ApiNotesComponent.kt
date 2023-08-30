package com.example.api.notes.di

import androidx.annotation.RestrictTo
import com.example.core.common.Feature
import dagger.Component
import kotlin.properties.Delegates.notNull

@[Feature Component(modules = [ApiNotesModule::class])]
internal interface ApiNotesComponent {

    @Component.Builder
    interface Builder {
        fun build(): ApiNotesComponent
    }
}

interface ApiNotesDeps {
}

interface ApiNotesDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: ApiNotesDeps

    companion object : ApiNotesDepsProvider by ApiNotesDepsStore
}

object ApiNotesDepsStore : ApiNotesDepsProvider {

    override var deps: ApiNotesDeps by notNull()
}