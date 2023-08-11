package com.example.feature.notes_list_ui.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.feature.notes_list_ui.R
import com.example.feature.notes_list_ui.databinding.FragmentNotesListBinding
import com.example.feature.notes_list_ui.di.NotesListComponentViewModel
import dagger.Lazy
import javax.inject.Inject

class NotesListFragment : Fragment(R.layout.fragment_notes_list) {

    @Inject
    internal lateinit var notesListViewModelFactory: Lazy<NotesListViewModel.Factory>

    private val viewModel: NotesListViewModel by viewModels {
        notesListViewModelFactory.get()
    }

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<NotesListComponentViewModel>()
            .notesListComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}