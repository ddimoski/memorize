package com.finki.mpip.memorize.helpers

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finki.mpip.memorize.view_model.FlashcardViewModel


class FlashcardViewModelFactory(private val mApplication: Application, private val deckId: Long) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FlashcardViewModel(mApplication, deckId) as T
    }
}