package com.example.feature.notes_list

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// Рекомендуемый подход к синхронизации, actor будет единственным местом затрагивающим общие данные,
// из-за чего уйдут проблемы синхронизации
class Counter(coroutineContext: CoroutineContext = EmptyCoroutineContext) {
    private val scope = CoroutineScope(coroutineContext)
    private var counter = 0

    private val counterCommands = scope.actor<CounterCommand>(capacity = Channel.BUFFERED) {
        for (command in this) {
            when (command) {
                is CounterCommand.Add -> counter += command.count
                is CounterCommand.Remove -> counter -= command.count
                is CounterCommand.Get -> command.response.complete(counter)
            }
        }
    }

    fun add(count: Int) {
        counterCommands.trySend(CounterCommand.Add(count))
    }

    fun remove(count: Int) {
        counterCommands.trySend(CounterCommand.Remove(count))
    }

    suspend fun get(): Int {
        val getCommand = CounterCommand.Get()
        counterCommands.send(getCommand)
        return getCommand.response.await()
    }
}

sealed class CounterCommand {
    class Add(val count: Int) : CounterCommand()
    class Remove(val count: Int) : CounterCommand()
    class Get(val response: CompletableDeferred<Int> = CompletableDeferred()) : CounterCommand()
}

// Еще один способ это использовать
// coroutineContext: CoroutineContext = newSingleThreadContext("Counter")
// не забудь его закрыть!! coroutineContext.close()
// за счет чего изменение буде выполняться на 1 потоке, как в примере с командами

// Использовать atomic тоже можно, но это часть java, поэтому для мультиплатформы будет не подходить

// Также можно использовать Mutex
class MutexCounter(coroutineContext: CoroutineContext = EmptyCoroutineContext) {
    private val scope = CoroutineScope(coroutineContext)
    private var counter = 0
    private val mutex = Mutex()


    // важный момент: корутина не сможет попасть в критическую секцию,
    // если она ее уже захватила в блокировку, в отличие от synchronized и lock в джаве
    suspend fun add(count: Int) {
        mutex.withLock {
            counter += count
        }
    }

}