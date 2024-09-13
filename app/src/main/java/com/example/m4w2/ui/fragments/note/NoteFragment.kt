package com.example.m4w2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import com.example.m4w2.R
import com.example.m4w2.databinding.FragmentNoteBinding
import com.example.m4w2.ui.utils.SharedPreference

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

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
    }

    private fun setupListeners() = with(this.binding){
        val sharedPreference = SharedPreference
        SharedPreference.unit(requireContext())

        btnSave.setOnClickListener {
            val et = noteEditText.text.toString()
            SharedPreference.text = et
            noteTxtSave.text = et
        }
        noteTxtSave.text = SharedPreference.text
    }
}