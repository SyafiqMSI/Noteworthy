package com.example.noteworthy.room.dao

import androidx.room.Dao
import androidx.room.Transaction
import com.example.noteworthy.room.BaseNote
import com.example.noteworthy.room.Label
import com.example.noteworthy.room.LabelsInBaseNote
import com.example.noteworthy.room.NoteworthyDatabase

@Dao
abstract class CommonDao(private val database: NoteworthyDatabase) {

    @Transaction
    open suspend fun deleteLabel(value: String) {
        val labelsInBaseNotes = database.getBaseNoteDao().getListOfBaseNotesByLabel(value).map { baseNote ->
            val labels = ArrayList(baseNote.labels)
            labels.remove(value)
            LabelsInBaseNote(baseNote.id, labels)
        }

    }

    @Transaction
    open suspend fun updateLabel(oldValue: String, newValue: String) {
        val labelsInBaseNotes = database.getBaseNoteDao().getListOfBaseNotesByLabel(oldValue).map { baseNote ->
            val labels = ArrayList(baseNote.labels)
            labels.remove(oldValue)
            labels.add(newValue)
            LabelsInBaseNote(baseNote.id, labels)
        }
        database.getBaseNoteDao().update(labelsInBaseNotes)
        database.getLabelDao().update(oldValue, newValue)
    }

    @Transaction
    open suspend fun importBackup(baseNotes: List<BaseNote>, labels: List<Label>) {
        database.getBaseNoteDao().insert(baseNotes)
        database.getLabelDao().insert(labels)
    }
}