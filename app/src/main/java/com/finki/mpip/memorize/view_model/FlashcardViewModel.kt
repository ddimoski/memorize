package com.finki.mpip.memorize.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.finki.mpip.memorize.database.AppDatabase
import com.finki.mpip.memorize.database.FlashcardRepository
import com.finki.mpip.memorize.model.Flashcard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlashcardViewModel(application: Application, deckId: Long) : AndroidViewModel(application) {
    private val flashcardsRepository: FlashcardRepository
    val allFlashcards: LiveData<List<Flashcard>>

    init {
        val flashcardDao = AppDatabase.getDatabase(application, viewModelScope).flashCardDao()
        flashcardsRepository = FlashcardRepository(flashcardDao)
        allFlashcards = flashcardsRepository.allFlashcards()
    }

    fun allFlashcardsByDeckId(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        flashcardsRepository.flashcardsByDeckId(id)
    }

    fun insert(newFlashcard: Flashcard) = viewModelScope.launch(Dispatchers.IO) {
        flashcardsRepository.insert(newFlashcard)
    }

    fun update(flashcard: Flashcard) = viewModelScope.launch(Dispatchers.IO) {
        flashcardsRepository.update(flashcard)
    }

    fun delete(flashcard: Flashcard) = viewModelScope.launch(Dispatchers.IO) {
        flashcardsRepository.delete(flashcard)
    }
}