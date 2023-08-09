package com.example.notes.di

import com.example.notes.MainActivity
import com.example.notes.TestFragment
import com.example.notes.di.testing.Analytics
import com.example.notes.di.testing.AnalyticsImpl
import com.example.notes.di.testing.TestDep
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Qualifier

@Component(modules = [AppModule::class, AnalyticsModule::class])
interface AppComponent {

    val testDep: TestDep // для provides

    fun inject(activity: MainActivity)
    fun inject(fragment: TestFragment)
}

@Module
class AppModule {
    @Provides
    fun provideTestDep(
        @AQualifier
        a: String,
        @Named("b")
        b: String,
        c: String
    ): TestDep {
        return TestDep(a, b, c)
    }

    @Provides
    @AQualifier
    fun provideAString(): String = "a"

    @Provides
    @Named("b")
    fun provideBString(): String = "b"

    @Provides
    fun provideDefaultString(): String = "c"

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME) // время жизни, runtime достаточно
annotation class AQualifier

@Module(includes = [AnalyticsBindModule::class])
class AnalyticsModule()

@Module
interface AnalyticsBindModule {
    @Binds
    fun bindAnalytics(analyticsImpl: AnalyticsImpl): Analytics
}