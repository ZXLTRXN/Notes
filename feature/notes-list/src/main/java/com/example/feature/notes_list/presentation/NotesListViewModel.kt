package com.example.feature.notes_list.presentation

import androidx.lifecycle.ViewModel
import com.example.api.notes.domain.GetAllNotesUseCase
import javax.inject.Inject
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NotesListViewModel @Inject constructor(
    private val getAllNotes: GetAllNotesUseCase
    ) :
    ViewModel() {
    fun test() {
        runBlocking {

            repeat(100) {
                // будет выполняться асинхронно
                launch {
                    doWork(it.toString())
                }
            }

            repeat(100) {
                // без корутины (launch, async) будет выполняться последовательно
                doWork(it.toString())
            }

        }
    }

    private suspend fun doWork(text: String) {
        delay(Random(2).nextLong(1500))
        println(text)
    }
}