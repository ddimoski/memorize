package com.finki.mpip.memorize.database

import androidx.lifecycle.LiveData
import com.finki.mpip.memorize.daos.FlashcardDao
import com.finki.mpip.memorize.model.Flashcard

class FlashcardRepository(private val flashcardDao: FlashcardDao) {
    fun flashcardsByDeckId(id: Long): LiveData<List<Flashcard>> = flashcardDao.getFlashcardsByDeckId(id)

    suspend fun insert(newFlashcard: Flashcard) = flashcardDao.addFlashcard(newFlashcard)

    suspend fun update(flashcard: Flashcard) = flashcardDao.updateFlashcard(flashcard)

    suspend fun delete(flashcard: Flashcard) = flashcardDao.deleteFlashcard(flashcard)
}