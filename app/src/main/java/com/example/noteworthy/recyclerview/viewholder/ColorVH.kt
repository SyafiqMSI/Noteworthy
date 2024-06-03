package com.example.noteworthy.recyclerview.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.noteworthy.databinding.RecyclerColorBinding
import com.example.noteworthy.miscellaneous.Operations
import com.example.noteworthy.recyclerview.ItemListener
import com.example.noteworthy.room.Color

class ColorVH(private val binding: RecyclerColorBinding, listener: ItemListener) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.CardView.setOnClickListener {
            listener.onClick(adapterPosition)
        }
    }

    fun bind(color: Color) {
        val value = Operations.extractColor(color, binding.root.context)
        binding.CardView.setCardBackgroundColor(value)
    }
}