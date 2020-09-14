package com.finki.mpip.memorize.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finki.mpip.memorize.model.Deck

@Dao
interface DeckDao {
    @Query("SELECT * FROM deck WHERE id = :id")
    fun getById(id: Long): Deck

    @Query("SELECT * FROM deck WHERE :id LIKE user_id")
    fun getAllDecksByUserId(id: String): LiveData<List<Deck>>

    @Query("SELECT * FROM deck")
    fun getAllDecks(): LiveData<List<Deck>>

    @Insert
    fun addDeck(newDeck: Deck): Long

    @Update
    fun updateDeck(deck: Deck): Int

    @Delete
    fun deleteDeck(deck: Deck): Int
}