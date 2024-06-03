package com.example.noteworthy.recyclerview.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.noteworthy.databinding.ErrorBinding
import com.example.noteworthy.image.ImageError

class ErrorVH(private val binding: ErrorBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(error: ImageError) {
        binding.Name.text = error.name
        binding.Description.text = error.description
    }
}