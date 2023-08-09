package com.example.notes.di

import com.example.notes.MainActivity
import com.example.notes.di.testing.Analytics
import com.example.notes.di.testing.AnalyticsImpl
import com.example.notes.di.testing.TestDep
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class, AnalyticsModule::class])
interface AppComponent {

    val testDep: TestDep

    fun inject(activity: MainActivity)
}

@Module
class AppModule {
    @Provides
    fun provideTestDep(): TestDep {
        return TestDep("a", 1)
    }

}

@Module(includes = [AnalyticsBindModule::class])
class AnalyticsModule()

@Module
interface AnalyticsBindModule {
    @Binds
    fun bindAnalytics(analyticsImpl: AnalyticsImpl): Analytics
}