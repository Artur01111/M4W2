package com.example.m4w2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m4w2.App
import com.example.m4w2.R
import com.example.m4w2.databinding.FragmentNoteBinding
import com.example.m4w2.ui.adapter.NoteAdapter
import com.example.m4w2.ui.data.models.NoteModel
import com.example.m4w2.ui.utils.OnClick

class NoteFragment : Fragment(){
    private val noteAdapter = NoteAdapter()
    private lateinit var binding: FragmentNoteBinding
    private var flag = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        getData()
    }

    private fun initialize() = with(binding){
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
        imgShape.setOnClickListener {
            if (flag) {
                imgShape.setImageResource(R.drawable.shapee)
                binding.rvNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                flag = false
            }else{
                imgShape.setImageResource(R.drawable.ic_shape_line)
                binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
                flag = true
            }
        }
    }

    private fun setupListeners() = with(binding) {
        btnPlus.setOnClickListener{
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAllNotes()?.observe(viewLifecycleOwner){ list ->
            noteAdapter.submitList(list)
        }
    }
}