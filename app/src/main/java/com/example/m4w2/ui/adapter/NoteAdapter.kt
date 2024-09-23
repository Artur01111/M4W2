package com.example.m4w2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.m4w2.data.models.NoteModel
import com.example.m4w2.databinding.ItemNotesBinding

class NoteAdapter : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    class ViewHolder(private val binding: ItemNotesBinding):
    RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NoteModel?) {
            binding.tvTitle.text = item?.title
            binding.tvDescription.text = item?.description
            binding.tvData.text = item?.date
            binding.tvTime.text = item?.time
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }


    class DiffCallback: DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}
