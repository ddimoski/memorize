package com.finki.mpip.memorize

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.finki.mpip.memorize.database.AppDatabase
import com.finki.mpip.memorize.helpers.FlashcardViewModelFactory
import com.finki.mpip.memorize.model.Flashcard
import com.finki.mpip.memorize.view_model.FlashcardViewModel

class StudyActivity : AppCompatActivity() {

    private lateinit var flashcardViewModel: FlashcardViewModel
    private var deckId = 0L
    private lateinit var listOfFlascards: List<Flashcard>
    private lateinit var currentFlashcard: Flashcard
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        val deckId = intent.getLongExtra("deckId", 0L)

        flashcardViewModel = ViewModelProvider(this,
            FlashcardViewModelFactory(this.application, deckId)
        )[FlashcardViewModel::class.java]

        flashcardViewModel.allFlashcards.observe(this, Observer { flashcards ->
            flashcards?.let {
                setListOfFlashcards(it)
                currentFlashcard = listOfFlascards.first()
                val currentFlascardText = findViewById<TextView>(R.id.flashcard_text)
                currentFlascardText.text = currentFlashcard.front
                currentFlascardText.setTypeface(null, Typeface.BOLD)
                val currentFlashcardCard = findViewById<CardView>(R.id.current_flashcard)
                currentFlashcardCard.setOnClickListener{
                    if(currentFlascardText.text == currentFlashcard.front){
                        currentFlascardText.text = currentFlashcard.back
                        currentFlascardText.setTypeface(null, Typeface.NORMAL)
                    }
                    else {
                        currentFlascardText.text = currentFlashcard.front
                        currentFlascardText.setTypeface(null, Typeface.BOLD)
                    }
                }

                val buttonNext = findViewById<Button>(R.id.button_next)
                buttonNext.setOnClickListener{
                    if (listOfFlascards.indexOf(currentFlashcard) != listOfFlascards.size - 1){
                        currentFlashcard = listOfFlascards.get(listOfFlascards.indexOf(currentFlashcard)+1)
                        currentFlascardText.text = currentFlashcard.front
                        currentFlascardText.setTypeface(null, Typeface.BOLD)
                    }
                    else {
                        Toast.makeText(this, "You have reached the end of the deck!", Toast.LENGTH_LONG).show()
                    }
                }

                val buttonBack = findViewById<Button>(R.id.button_back)
                buttonBack.setOnClickListener{
                    if (listOfFlascards.indexOf(currentFlashcard) != 0){
                        currentFlashcard = listOfFlascards.get(listOfFlascards.indexOf(currentFlashcard)-1)
                        currentFlascardText.text = currentFlashcard.front
                        currentFlascardText.setTypeface(null, Typeface.BOLD)
                    }
                    else {
                        Toast.makeText(this, "You have reached the start of the deck!", Toast.LENGTH_LONG).show()
                    }
                }

                val buttonStop = findViewById<Button>(R.id.button_stop)
                buttonStop.setOnClickListener{
                    finish()
                }
            }
        })

    }

    fun setListOfFlashcards(list: List<Flashcard>): List<Flashcard> {
        this.listOfFlascards = list
        return this.listOfFlascards
    }
}