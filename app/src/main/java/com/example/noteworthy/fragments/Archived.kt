package com.example.noteworthy.fragments

import com.example.noteworthy.R

class Archived : NoteworthyFragment() {

    override fun getBackground() = R.drawable.archive

    override fun getObservable() = model.archivedNotes
}