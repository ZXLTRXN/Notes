package com.example.feature.notes_list.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun defaultSharedFlowKco12() {
    val scope = CoroutineScope(Dispatchers.Default)
    val sharedFlow = MutableSharedFlow<Int>(onBufferOverflow = BufferOverflow.DROP_OLDEST, extraBufferCapacity = 3)

    sharedFlow
        .onStart { println("subscribed") }
        .onEach {
            println("first: $it")
        }.launchIn(scope)

    scope.launch {
        delay(400)
        println("launch")
        sharedFlow.emit(5)
        sharedFlow.emit(3)
        sharedFlow.emit(1)
        println("launch end")
        delay(400) // emit происходит не мгновенно, поэтому нужно время, чтобы onEach сработал,
        // в случае когда strategy не SUSPEND
        scope.cancel()
    }

    while (scope.isActive) { }
}

fun shareInKco12() {
    val scope = CoroutineScope(Dispatchers.Default)
    val sharedFlow = flow {
        emit(5)
        emit(3)
        emit(1)
        Thread.sleep(50)
        scope.cancel()
    }.shareIn(scope, started = SharingStarted.Eagerly)

    sharedFlow.onEach {
        println("first: $it")
    }.launchIn(scope)

    while (scope.isActive) { }

}

fun defaultStateFlow() {
    val scope = CoroutineScope(Dispatchers.Default)
    val stateFlow = MutableStateFlow("Author: Filip")

    println(stateFlow.value)

    scope.launch {
        stateFlow.collect {
            println(it)
        }
    }

    stateFlow.value = "Author: Luka" // если запустить вместе с теми что ниже, то будет проигнорировано,
    // поэтому стоит использовать методы ниже

    stateFlow.tryEmit("FPE: Max")

    scope.launch {
        stateFlow.emit("TE: Godfred")
    }

    Thread.sleep(50)
    scope.cancel()

    while (scope.isActive) { }
}