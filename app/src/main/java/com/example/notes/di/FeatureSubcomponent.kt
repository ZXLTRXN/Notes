package com.example.notes.di

import dagger.Module
import dagger.Subcomponent
import javax.inject.Scope

// Subcomponent очень плохи для модульных проектов, поскольку все сабкопоненты вкладываются в 1
// главный DaggerAppComponent и жестко связаны с ним.
// Таким же образом работает hilt, поэтому он тоже для многомодульных проектов плох
@Feature
@Subcomponent(modules = [FeatureModule::class])
interface FeatureComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): FeatureComponent
    }
}

@Scope
annotation class Feature

@Module
class FeatureModule

