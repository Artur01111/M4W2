package com.example.m4w2.ui.fragments.note

import android.app.AlertDialog
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
import com.example.m4w2.ui.interfaces.OnClickItem
import com.example.m4w2.utils.PreferenceHelper

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpListeners()
        getData()
    }

    private fun initialize() {
        binding.homeRecyclerView.apply {
            val sharedPreferences = PreferenceHelper()
            sharedPreferences.unit(requireContext())
            if (sharedPreferences.isRecyclerViewGrid) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                binding.btnChangerRv.setImageResource(R.drawable.menu)
            } else if (!sharedPreferences.isRecyclerViewGrid) {
                layoutManager = LinearLayoutManager(requireContext())
                binding.btnChangerRv.setImageResource(R.drawable.shape)
            }
            adapter = noteAdapter
        }
    }

    private fun setUpListeners() = with(binding) {
        btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.noteDetailFragment)
        }
        btnChangerRv.setOnClickListener {
            val sharedPreferences = PreferenceHelper()
            sharedPreferences.unit(requireContext())
            if (!sharedPreferences.isRecyclerViewGrid) {
                btnChangerRv.setImageResource(R.drawable.menu)
                homeRecyclerView.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    sharedPreferences.isRecyclerViewGrid = true
                }
            }else if (sharedPreferences.isRecyclerViewGrid) {
                btnChangerRv.setImageResource(R.drawable.shape)
                homeRecyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    sharedPreferences.isRecyclerViewGrid = false
                }
            }
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
            setTitle("Вы действительно хотите удалить эту заметку?")
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