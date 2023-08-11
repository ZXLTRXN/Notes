package com.example.notes.di

import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
annotation class AppScope

@[AppScope Component(modules = [AppModule::class], dependencies = [AppDeps::class])]
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun appDeps(deps: AppDeps): Builder
        fun build(): AppComponent
    }
}

@Module(includes = [AppBindsModule::class])
class AppModule

@Module
interface AppBindsModule