package com.example.notes.di

import android.content.Context
import dagger.Component
import dagger.Module
import javax.inject.Scope

// Subcomponent очень плохи для модульных проектов, поскольку все subcomponents вкладываются в 1
// главный DaggerAppComponent и жестко связаны с ним.
// Таким же образом работает hilt, поэтому он тоже для многомодульных проектов плох
@Feature
@Component(modules = [FeatureModule::class], dependencies = [FeatureDeps::class])
interface FeatureComponent {

    @Component.Builder
    interface Builder {
        fun deps(featureDeps: FeatureDeps): Builder
        fun build(): FeatureComponent
    }
}

interface FeatureDeps { // интерфейс задает нужные нам зависимости, а appComponent их предоставит
    fun context(): Context
}

@Scope
annotation class Feature

@Module
class FeatureModule

