package com.example.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notes.di.appComponent
import com.example.notes.di.testing.TestViewModel
import com.example.notes.di.testing.TestViewModelFactory
import javax.inject.Inject

class TestFragment : Fragment() {

    // для этой factory lazy и provider неприменимы, поскольку AssistedInject это не поддерживает
    @Inject lateinit var factory: TestViewModelFactory.Factory

    private val viewModel: TestViewModel by viewModels {
        factory.create(123L)
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this) // именно тут
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()
    }
}