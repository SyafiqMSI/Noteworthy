package com.example.noteworthy.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Label(@PrimaryKey val value: String)