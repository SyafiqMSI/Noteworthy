package com.example.noteworthy.preferences

import com.example.noteworthy.R

sealed interface TextInfo {

    val title: Int

    val key: String
    val defaultValue: String
}

object AutoBackup : TextInfo {
    const val emptyPath = "emptyPath"

    override val title = R.string.auto_backup

    override val key = "autoBackup"
    override val defaultValue = emptyPath
}