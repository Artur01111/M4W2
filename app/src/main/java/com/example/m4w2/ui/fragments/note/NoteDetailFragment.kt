package com.example.m4w2.ui.fragments.note

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m4w2.App
import com.example.m4w2.data.models.NoteModel
import com.example.m4w2.databinding.FragmentNoteDetailBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var noteId:Int = -1
    var timeText = ""
    var dateText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentDate = Calendar.getInstance().time
        val dateFormat: DateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
        dateText = dateFormat.format(currentDate)
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        timeText = timeFormat.format(currentDate)
        binding.txtTime.text = timeText
        binding.txtDate.text = dateText
        updateNote()

        setupListeners()
        setupTextChangedListener()
        checkButtonVisibility()
    }

    private fun updateNote() {
        arguments?.let { args ->
            noteId = args.getInt("noteId", -1)
        }
        if (noteId != -1){
            val argsNote = App.appDatabase?.noteDao()?.getNoteBtId(noteId)
            argsNote?.let { item ->
                binding.etTitle.setText(item.title)
                binding.etDescription.setText(item.description)
            }
        }
    }

    private fun setupListeners() {
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnFinished.setOnClickListener {
            val etTitle = binding.etTitle.text.toString()
            val etDescription = binding.etDescription.text.toString()
            val timeText = binding.txtTime.text.toString()
            val dateText = binding.txtDate.text.toString()
            if (noteId != -1){
                val updateNote = NoteModel(dateText, timeText, etTitle, etDescription)
                updateNote.id = noteId
                App.appDatabase?.noteDao()?.updateNote(updateNote)
            }else{
                App.appDatabase?.noteDao()?.insertNote(NoteModel(dateText, timeText, etTitle, etDescription))
            }
            findNavController().navigateUp()
        }
    }

    private fun setupTextChangedListener() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                checkButtonVisibility()
            }
        })

        binding.etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                checkButtonVisibility()
            }
        })
    }

    private fun checkButtonVisibility() {
        val titleText = binding.etTitle.text.toString().trim()
        val descriptionText = binding.etDescription.text.toString().trim()

        binding.btnFinished.visibility = if (titleText.isNotEmpty() && descriptionText.isNotEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}