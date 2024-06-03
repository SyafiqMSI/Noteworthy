package com.example.noteworthy.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.noteworthy.room.Label
import com.example.noteworthy.room.NoteworthyDatabase

class LabelModel(app: Application) : AndroidViewModel(app) {

    private val database = NoteworthyDatabase.getDatabase(app)
    private val labelDao = database.getLabelDao()
    val labels = labelDao.getAll()

    fun insertLabel(label: Label, onComplete: (success: Boolean) -> Unit) =
        executeAsyncWithCallback({ labelDao.insert(label) }, onComplete)
}