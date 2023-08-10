package com.example.notes.di

import com.example.notes.di.testing.Analytics
import com.example.notes.di.testing.AnalyticsImpl
import dagger.Binds
import dagger.Module

@Module(includes = [AnalyticsBindModule::class])
class AnalyticsModule

@Module
interface AnalyticsBindModule {
    @Binds
    fun bindAnalytics(analyticsImpl: AnalyticsImpl): Analytics
}