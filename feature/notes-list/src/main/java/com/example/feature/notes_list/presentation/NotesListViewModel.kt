package com.example.feature.notes_list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.api.notes.domain.GetAllNotesUseCase
import javax.inject.Inject
import kotlin.random.Random
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

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

    // пример обработки исключений
    fun tryCatchTest() {

        defaultLaunch()
        defaultAsync()
        catchEntireScope()
        finally()

    }

    private fun defaultLaunch() {
        runBlocking {
            // аналогично обрабатывается actor coroutine builder
            launch() {
                try {
                    throwing("launch")
                } catch (ex: RuntimeException) {
                    printEx(ex)
                }
            }

            // не сработает, ошибка пойдет вверх
//            try {
//                launch() {
//                    throwing("launch outer")
//                }
//            } catch (ex: RuntimeException) {
//                Log.e("TAG", "tryCatch: ", ex)
//            }
        }
    }

    private fun defaultAsync() {
        runBlocking {
            // producer обрабатывается аналогично, но без ухищрений
            // А с async, если делать без этих ухищрений, то ошибка пробросится вверх

            val deferred: Deferred<String> = async(Job()) {
                throwing("async new Job")
            }
            try {
                deferred.await()
            } catch (ex: RuntimeException) {
                printEx(ex)
            }

            // либо

            supervisorScope {
                val deferred1: Deferred<String> = async() {
                    throwing("async with supervisor")
                }
                try {
                    deferred1.await()
                } catch (ex: RuntimeException) {
                    printEx(ex)
                }
            }

        }
    }

    private fun catchEntireScope() {
        runBlocking {
            // coroutineScope и supervisorScope'ами можно ловить все исключения внутри, родителям пробрасываться не будет
            try {
                coroutineScope {
                    launch {
                        throwing("launch in coroutineScope")
                    }

                }
            } catch (ex: RuntimeException) {
                printEx(ex)
            }
        }
    }

    private fun finally() {
        runBlocking {
            try {
                throwing()
            } catch (ex: RuntimeException) {
                printEx(ex)
            } finally {
                // корутина может быть отменена и при вызове suspend функции
                // произойдет остановка выполнения корутины, для избежания этого используем
                withContext(NonCancellable) {
                    Log.i("TAG", "finally: ")
                }
            }
        }
    }

    private fun printEx(ex: RuntimeException) {
        Log.e("TAG", "printEx: ${ex.message}")
    }

    private fun throwing(v: String = "empty"): String {
        throw NullPointerException(v)
    }
}