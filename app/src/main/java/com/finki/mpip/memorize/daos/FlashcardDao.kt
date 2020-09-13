package com.finki.mpip.memorize.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finki.mpip.memorize.model.Flashcard

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcard")
    suspend fun getAll(): List<Flashcard>

    @Query("SELECT * FROM flashcard WHERE id = :id")
    suspend fun getById(id: Long): Flashcard

    @Query("SELECT * FROM flashcard WHERE :id = deck_id")
    fun getFlashcardsByDeckId(id: Long): LiveData<List<Flashcard>>

    @Insert
    suspend fun addFlashcard(newFlashcard: Flashcard): Long

    @Update
    suspend fun updateFlashcard(flashcard: Flashcard): Int

    @Delete
    suspend fun deleteFlashcard(flashcard: Flashcard): Int

    @Query("DELETE FROM flashcard")
    suspend fun deleteAll()
}