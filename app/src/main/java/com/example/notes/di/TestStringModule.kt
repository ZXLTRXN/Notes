package com.example.notes.di

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Qualifier

@Module
class TestStringsModule {
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
@Retention(AnnotationRetention.RUNTIME) // время жизни аннотации, runtime достаточно
annotation class AQualifier