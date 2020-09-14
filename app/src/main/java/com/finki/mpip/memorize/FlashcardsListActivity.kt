package com.finki.mpip.memorize

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finki.mpip.memorize.adapters.FlashcardListAdapter
import com.finki.mpip.memorize.helpers.FlashcardViewModelFactory
import com.finki.mpip.memorize.model.Flashcard
import com.finki.mpip.memorize.view_model.FlashcardViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.doAsync


private const val RC_NEW_FLASHCARD = 4

class FlashcardsListActivity : AppCompatActivity() {

    private lateinit var flashcardViewModel: FlashcardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcards_list)

        val deckId = intent.getLongExtra("deckId", 0L)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener {
            val intent = Intent(this, AddFlashcardActivity::class.java)
            startActivityForResult(intent, RC_NEW_FLASHCARD)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.flashcard_recyclerview)
        val adapter = FlashcardListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        flashcardViewModel = ViewModelProvider(this,
            FlashcardViewModelFactory(this.application, deckId))[FlashcardViewModel::class.java]
        //ViewModelProvider(this).get(FlashcardViewModel::class.java)
        //SET THE ID
        //flashcardViewModel.deckId = deckId

        flashcardViewModel.allFlashcards.observe(this, Observer { flashcards ->
            // Update the cached copy of the words in the adapter.
            flashcards?.let { adapter.setFlashcards(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_NEW_FLASHCARD && resultCode == Activity.RESULT_OK) {
            val front = data?.getStringExtra(AddFlashcardActivity.EXTRA_FRONT)
            val back = data?.getStringExtra(AddFlashcardActivity.EXTRA_BACK)
            flashcardViewModel.insert(Flashcard(front!!, back!!, 0))
        }
    }
}