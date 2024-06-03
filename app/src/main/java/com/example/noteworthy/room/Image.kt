package com.example.noteworthy.room

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(var name: String, val mimeType: String) : Parcelable