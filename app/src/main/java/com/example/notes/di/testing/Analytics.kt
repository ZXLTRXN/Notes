package com.example.notes.di.testing

import android.content.Context
import javax.inject.Inject

fun interface Analytics {
    fun send()
}

class AnalyticsImpl @Inject constructor(
    private val testDep: TestDep,
    private val context: Context
) : Analytics {
    override fun send() {
        println("send $testDep with $context")
    }
}