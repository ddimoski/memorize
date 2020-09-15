package com.finki.mpip.memorize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finki.mpip.memorize.adapters.FlashcardListAdapter
import com.finki.mpip.memorize.helpers.FlashcardViewModelFactory
import com.finki.mpip.memorize.view_model.FlashcardViewModel

class StudyActivity : AppCompatActivity() {

    private lateinit var flashcardViewModel: FlashcardViewModel
    private var deckId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        val deckId = intent.getLongExtra("deckId", 0L)

        flashcardViewModel = ViewModelProvider(this,
            FlashcardViewModelFactory(this.application, deckId)
        )[FlashcardViewModel::class.java]


    }
}