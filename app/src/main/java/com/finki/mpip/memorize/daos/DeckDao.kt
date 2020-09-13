package com.finki.mpip.memorize.daos

import androidx.room.*
import com.finki.mpip.memorize.model.Deck
import com.finki.mpip.memorize.model.Flashcard

@Dao
interface DeckDao {
    @Query("SELECT * FROM deck WHERE id = :id")
    fun getById(id: Long): Deck

    /*@Query("SELECT flashcards FROM deck WHERE :id = user_id")
    fun getAllFlashcardsByUserId(id: String): List<Flashcard>*/

    @Insert
    fun addDeck(newDeck: Deck): Long

    @Update
    fun updateDeck(deck: Deck): Int

    @Delete
    fun deleteDeck(deck: Deck): Int
}