package com.finki.mpip.memorize.database

import androidx.lifecycle.LiveData
import com.finki.mpip.memorize.daos.DeckDao
import com.finki.mpip.memorize.model.Deck

class DeckRepository(private val deckDao: DeckDao) {
    fun allDecks(): LiveData<List<Deck>> = deckDao.getAllDecks()

    fun allDecksByUserId(id: String): LiveData<List<Deck>> = deckDao.getAllDecksByUserId(id)

    suspend fun insert(newDeck: Deck) = deckDao.addDeck(newDeck)

    suspend fun update(deck: Deck) = deckDao.updateDeck(deck)

    suspend fun delete(deck: Deck) = deckDao.deleteDeck(deck)
}