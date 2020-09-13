package com.finki.mpip.memorize.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finki.mpip.memorize.model.Flashcard

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcard")
    fun getAll(): List<Flashcard>

    @Query("SELECT * FROM flashcard WHERE id = :id")
    fun getById(id: Long): Flashcard

    @Query("SELECT * FROM flashcard WHERE :id = deck_id")
    fun getFlashcardsByDeckId(id: Long): LiveData<List<Flashcard>>

    @Insert
    fun addFlashcard(newFlashcard: Flashcard): Long

    @Update
    fun updateFlashcard(flashcard: Flashcard): Int

    @Delete
    fun deleteFlashcard(flashcard: Flashcard): Int

}