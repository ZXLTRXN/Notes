package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.di.TestDepClass
import com.example.notes.di.appComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var test: TestDepClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        println(test)
    }
}