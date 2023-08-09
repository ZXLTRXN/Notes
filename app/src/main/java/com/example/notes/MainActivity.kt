package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.di.testing.TestDep
import com.example.notes.di.appComponent
import com.example.notes.di.testing.Analytics
import dagger.Lazy
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var test: Lazy<TestDep> // Lazy именно из dagger!!!
    // Так же можно использовать и Provider, его особенность в том, ято он возвращает каждый раз новый инстанс

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("after Inject")
        appComponent.inject(this)
        println(test.get().a)
    }

    @Inject // вызовется сразу после создания объекта 1 раз, именно для таких одноразовых случаев Inject в метод и нужен
    fun sendAnalytics(analytics: Analytics) {
        analytics.send()
    }
}