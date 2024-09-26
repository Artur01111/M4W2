package com.example.m4w2.ui.fragments.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m4w2.App
import com.example.m4w2.R
import com.example.m4w2.data.models.NoteModel
import com.example.m4w2.databinding.FragmentNoteBinding
import com.example.m4w2.interfaces.OnClickItem
import com.example.m4w2.ui.adapter.NoteAdapter

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this)

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

    private fun initialize() {
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListeners() = with(binding) {
        btnPlus.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }

        menu.setOnClickListener {
        }
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { list ->
            noteAdapter.submitList(list)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить заметку?")
            setPositiveButton("Да") { dialog, _ ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}