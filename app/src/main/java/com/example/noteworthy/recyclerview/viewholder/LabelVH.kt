package com.example.noteworthy.recyclerview.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.noteworthy.databinding.RecyclerLabelBinding
import com.example.noteworthy.recyclerview.ItemListener

class LabelVH(private val binding: RecyclerLabelBinding, listener: ItemListener) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            listener.onClick(adapterPosition)
        }

        binding.root.setOnLongClickListener {
            listener.onLongClick(adapterPosition)
            return@setOnLongClickListener true
        }
    }

    fun bind(value: String) {
        binding.root.text = value
    }
}