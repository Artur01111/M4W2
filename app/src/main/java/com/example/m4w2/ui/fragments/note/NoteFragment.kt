package com.example.m4w2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m4w2.R
import com.example.m4w2.databinding.FragmentNoteBinding
import com.example.m4w2.ui.App
import com.example.m4w2.ui.adapter.NoteAdapter
import com.example.m4w2.ui.utils.SharedPreference

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        initialize()
        getData()
    }

    private fun initialize() {
         binding.recyclerViewNotes.apply {
             layoutManager = LinearLayoutManager(requireContext())
             adapter = noteAdapter
         }
    }

    private fun setupListeners() = with(this.binding){
        addBtn.setOnClickListener{
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){list ->
            noteAdapter.submitList(list)
        }
    }
}