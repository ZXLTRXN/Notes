package com.example.notes.di.testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TestViewModel(
    private val newsId: Long,
    private val analytics: Analytics
) : ViewModel() {

    fun test() {
        println("from viewModel $newsId")
        analytics.send()
    }

}

class TestViewModelFactory @AssistedInject constructor(
    @Assisted("newsId") private val newsId: Long,
    private val analytics: Analytics
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TestViewModel(newsId, analytics) as T
    }


    @AssistedFactory
    interface Factory {
        fun create(@Assisted("newsId") newsId: Long): TestViewModelFactory
    }
}

// Упростить этот код нам поможет Assisted Inject выше
//    class TestViewModelFactory(
//        private val newsId: Long,
//        private val analytics: Analytics
//    ) : ViewModelProvider.Factory {
//
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            require(modelClass == TestViewModel::class)
//            return TestViewModel(newsId, analytics) as T
//        }
//
//        class Factory @Inject constructor(val analytics: Analytics) {
//            fun create(newsId: Long): TestViewModelFactory {
//                return TestViewModelFactory(newsId, analytics)
//            }
//        }
//    }