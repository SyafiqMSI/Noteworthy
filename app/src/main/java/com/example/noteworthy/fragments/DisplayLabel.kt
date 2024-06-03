package com.example.noteworthy.fragments

import androidx.lifecycle.LiveData
import com.example.noteworthy.R
import com.example.noteworthy.miscellaneous.Constants
import com.example.noteworthy.room.Item

class DisplayLabel : NoteworthyFragment() {

    override fun getBackground() = R.drawable.label

    override fun getObservable(): LiveData<List<Item>> {
        val label = requireNotNull(requireArguments().getString(Constants.SelectedLabel))
        return model.getNotesByLabel(label)
    }
}