package com.finki.mpip.memorize.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Flashcard(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "front") val front: String,
    @ColumnInfo(name = "back")  val back: String,
    @ColumnInfo(name = "category") val category: String?
)