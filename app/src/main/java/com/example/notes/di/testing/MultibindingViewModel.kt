package com.example.notes.di.testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass


class MainViewModel @Inject constructor(private val testDep: TestDep) : ViewModel() {

    fun test() { println("MainViewModel test $testDep") }
}

class ListViewModel @Inject constructor() : ViewModel()

class DetailsViewModel @Inject constructor() : ViewModel()


// Multibinding позволяет вносить зависимости в map или set и доставать их все вместе.
// map пригодится для создания viewModels без постоянного создания вручную фабрик как в этом примере.
// set же можно использовать в случаях когда нужно несколько зависимостей подчиняющихся 1 интерфейсу,
//  и Multibinding позволит расширять количество этих зависимостей чисто, просто добавляя их в сет.
// Assisted inject этой фичей не поддерживается, его надо делать как в классе TestViewModel.

class MultiViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelFactories.getValue(modelClass as Class<ViewModel>).get() as T
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)