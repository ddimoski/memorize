package com.finki.mpip.memorize.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finki.mpip.memorize.R
import com.finki.mpip.memorize.model.Flashcard

class FlashcardListAdapter internal constructor(context: Context
) : RecyclerView.Adapter<FlashcardListAdapter.FlashcardViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var flashcards = emptyList<Flashcard>() // Cached copy of words

    inner class FlashcardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flashcardItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return FlashcardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlashcardViewHolder, position: Int) {
        val current = flashcards[position]
        holder.flashcardItemView.text = current.front
    }

    internal fun setFlashcards(flashcards: List<Flashcard>) {
        this.flashcards = flashcards
        notifyDataSetChanged()
    }

    override fun getItemCount() = flashcards.size
}