package com.finki.mpip.memorize.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(/*foreignKeys = arrayOf(
    ForeignKey(entity = Deck::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("deck_id"),
    onDelete = ForeignKey.CASCADE)
)*/)
data class Flashcard(
    @ColumnInfo(name = "front") var front: String,
    @ColumnInfo(name = "back")  var back: String,
    @ColumnInfo(name = "deck_id", index = true) var deckId: Long
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}