package com.finki.mpip.memorize.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.finki.mpip.memorize.database.AppDatabase
import com.finki.mpip.memorize.database.DeckRepository
import com.finki.mpip.memorize.model.Deck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeckViewModel(application: Application) : AndroidViewModel(application) {
    private val deckRepository: DeckRepository
    val userId: String
    val allDecks: LiveData<List<Deck>>

    init {
        userId = ""
        val deckDao = AppDatabase.getDatabase(application, viewModelScope).deckDao()
        deckRepository = DeckRepository(deckDao)
        allDecks = deckRepository.allDecks() //TODO: CHANGE TO USER ID
    }

    fun insert(deck: Deck) = viewModelScope.launch(Dispatchers.IO) {
        deckRepository.insert(deck)
    }
}