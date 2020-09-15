package com.finki.mpip.memorize.database

import androidx.lifecycle.LiveData
import com.finki.mpip.memorize.adapters.FlashcardListAdapter
import com.finki.mpip.memorize.daos.FlashcardDao
import com.finki.mpip.memorize.model.Flashcard

class FlashcardRepository(private val flashcardDao: FlashcardDao) {
    fun flashcardsByDeckId(id: Long): LiveData<List<Flashcard>> = flashcardDao.getFlashcardsByDeckId(id)

    fun allFlashcards(): LiveData<List<Flashcard>> = flashcardDao.getAll()

    fun getAllFlashcards(): List<Flashcard> = flashcardDao.getAllFlashcards()

    suspend fun insert(newFlashcard: Flashcard) = flashcardDao.addFlashcard(newFlashcard)

    suspend fun update(flashcard: Flashcard) = flashcardDao.updateFlashcard(flashcard)

    suspend fun delete(flashcard: Flashcard) = flashcardDao.deleteFlashcard(flashcard)
}