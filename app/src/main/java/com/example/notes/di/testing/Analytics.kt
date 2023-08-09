package com.example.notes.di.testing

import javax.inject.Inject

fun interface Analytics {
    fun send()
}

class AnalyticsImpl @Inject constructor(private val testDep: TestDep): Analytics {
    override fun send() {
        println("send $testDep")
    }
}