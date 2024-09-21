package com.example.m4w2.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.m4w2.databinding.ItemNoteBinding
import com.example.m4w2.ui.data.models.NoteModel
import com.example.m4w2.ui.fragments.note.NoteDetailFragment
import com.example.m4w2.ui.utils.OnClick

class NoteAdapter : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {

    private var onNoteClickListener: OnClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun setOnNoteClickListener(listener: OnClick) {
        onNoteClickListener = listener
    }

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root){

        @SuppressLint("ResourceAsColor")
        fun onBind(item: NoteModel) {
            binding.apply {
                tvTitle.text = item.title
                tvDescription.text = item.content
                tvData.text = item.date
                tvTime.text = item.time
                itemView.setBackgroundColor(item.color)
                itemView.setOnClickListener{
                    onNoteClickListener?.onItemClick(item)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.title == newItem.title && oldItem.content == newItem.content
        }
    }
}