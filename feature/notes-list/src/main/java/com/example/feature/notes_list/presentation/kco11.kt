package com.example.feature.notes_list.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
fun differentContextsViaChannelFlowKco11(scope: CoroutineScope) {
    // такой подход (channelFlow) позволяет делать продюсер и консюмер в разных контекстах, либо используй flowOn
    val flowOfStrings = channelFlow<String> {
        withContext(Dispatchers.Default) {
            for(number in 0..100) {
                send("emitting: $number  on ${Thread.currentThread()}")
            }
        }

    }

    scope.launch {
        flowOfStrings.collect { println(it) }
        println("The code still works!")
    }
}

fun defaultKco11(scope: CoroutineScope) {
    val flowOfStrings = flow {
        emit("")

        for (number in 0..100) {
            if (number == 55) throw IllegalArgumentException()
            emit("Emitting: $number on ${Thread.currentThread().name}")
        }
    }

    scope.launch() {
        flowOfStrings
            .flowOn(Dispatchers.Default)
            .map { "$it, mapped on ${Thread.currentThread().name}" }
            .flowOn(Dispatchers.IO)
            .catch { ex ->
                println(ex)
                emit("fallback ${Thread.currentThread().name}")
            }
            .flowOn(Dispatchers.Main)
            .collect {
                println(it)
                println("collect context ${Thread.currentThread().name}")
            }
        println("launch context ${Thread.currentThread().name}") // совпадет с коллектом
        println("The code still works!")
    }
}