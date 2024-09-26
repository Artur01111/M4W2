package com.example.m4w2.ui.fragments.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Query
import com.example.m4w2.databinding.FragmentChatBinding
import com.example.m4w2.ui.adapter.ChatAdapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val chatAdapter = ChatAdapter()
    private val db = Firebase.firestore
    private lateinit var query: com.google.firebase.firestore.Query
    private lateinit var listener: ListenerRegistration


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializes()
        setupListeners()
        observeMessage()
    }

    private fun observeMessage() {
        query = db.collection("user")
        listener = query.addSnapshotListener { value, error ->
            if (error != null){
                return@addSnapshotListener
            }
            value?.let { listValue ->
                val messages = mutableListOf<String>()
                for (doc in listValue.documents){
                    val sms = doc.getString("name")
                    sms?.let{
                        messages.add(it)
                    }
                }
                chatAdapter.submitList(messages)
            }
        }
    }

    private fun setupListeners() {
        binding.sendBtn.setOnClickListener {
            val user = hashMapOf(
                "name" to binding.etMessage.text.toString()
            )
            db.collection("user").add(user).addOnCompleteListener{}
            binding.etMessage.text.clear()
        }
    }

    private fun initializes() {
        binding.rvChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener.remove()
    }
}