package com.example.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notes.di.appComponent
import com.example.notes.di.testing.MainViewModel
import com.example.notes.di.testing.MultiViewModelFactory
import javax.inject.Inject

class TestFragment : Fragment() {

    // для этой factory lazy и provider неприменимы, поскольку AssistedInject это не поддерживает
    @Inject
    lateinit var factory: MultiViewModelFactory

    private val viewModel: MainViewModel by lazy {
        factory.create(MainViewModel::class.java)
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