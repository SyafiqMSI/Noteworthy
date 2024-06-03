package com.example.noteworthy.recyclerview

interface ItemListener {

    fun onClick(position: Int)

    fun onLongClick(position: Int)
}