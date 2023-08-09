package com.example.notes.di

import com.example.notes.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {

    val testDep: TestDepClass

    fun inject(activity: MainActivity)
}

@Module
object AppModule {

    @Provides
    fun provideTestDep(): TestDepClass {
        return TestDepClass("a", 1)
    }

}