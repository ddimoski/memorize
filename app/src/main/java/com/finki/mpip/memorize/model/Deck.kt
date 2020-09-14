package com.finki.mpip.memorize.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(/*foreignKeys = arrayOf(
    ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE)
)*/)
data class Deck(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "user_id", index = true) var userId: String,
    @ColumnInfo(name = "flashcards") var flashcards: List<Flashcard> = ArrayList()
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
    fun addFlashcard(newFlashcard: Flashcard) {
        (flashcards as ArrayList).add(newFlashcard)
    }
}