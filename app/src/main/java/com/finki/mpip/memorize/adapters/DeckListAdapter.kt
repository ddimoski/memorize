package com.finki.mpip.memorize.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finki.mpip.memorize.R
import com.finki.mpip.memorize.model.Deck
import com.finki.mpip.memorize.model.Flashcard

class DeckListAdapter internal constructor(context: Context
) : RecyclerView.Adapter<DeckListAdapter.DeckViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var decks = emptyList<Deck>()

    inner class DeckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deckItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):DeckViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return DeckViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeckListAdapter.DeckViewHolder, position: Int) {
        val current = decks[position]
        holder.deckItemView.text = current.name
    }

    internal fun setDecks(decks: List<Deck>) {
        this.decks = decks
        notifyDataSetChanged()
    }

    override fun getItemCount() = decks.size
}