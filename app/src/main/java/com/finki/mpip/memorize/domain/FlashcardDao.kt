package com.finki.mpip.memorize.domain

import androidx.room.*

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcard WHERE category LIKE :category")
    fun getAllByCategory(category: String): List<Flashcard>

    @Query("SELECT * FROM flashcard")
    fun getAll(): List<Flashcard>

    @Query("SELECT * FROM flashcard WHERE id = :id")
    fun getById(id: Long): Flashcard

    @Insert
    fun addFlashcard(newFlashcard: Flashcard): Long

    @Update
    fun updateFlashcard(flashcard: Flashcard): Int

    @Delete
    fun deleteFlashcard(flashcard: Flashcard): Int

}