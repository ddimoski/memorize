package com.finki.mpip.memorize

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finki.mpip.memorize.adapters.DeckListAdapter
import com.finki.mpip.memorize.adapters.FlashcardListAdapter
import com.finki.mpip.memorize.model.Deck
import com.finki.mpip.memorize.model.Flashcard
import com.finki.mpip.memorize.view_model.DeckViewModel
import com.finki.mpip.memorize.view_model.FlashcardViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val RC_NEW_DECK = 3

/**
 * A simple [Fragment] subclass.
 * Use the [DecksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DecksFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var deckViewModel: DeckViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        sharedPreferences = activity?.getSharedPreferences("com.finki.mpip.memorize", Context.MODE_PRIVATE)!!
        val userId = sharedPreferences.getString("userId", "empty")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View? = inflater.inflate(R.layout.fragment_decks, container, false)
        val fab = view?.findViewById<FloatingActionButton>(R.id.fab_decks)
        fab?.setOnClickListener {
            val intent = Intent(activity, AddDeckActivity::class.java)
            startActivityForResult(intent, RC_NEW_DECK)
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.deck_recyclerview)
        val adapter = DeckListAdapter(context!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        deckViewModel = ViewModelProvider(this).get(DeckViewModel::class.java)
        //TODO: ALWAYS SET DECKID MANUALLY
        deckViewModel.allDecks.observe(viewLifecycleOwner, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setDecks(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_NEW_DECK && resultCode == Activity.RESULT_OK) {
            val deckName = data?.getStringExtra(AddDeckActivity.EXTRA_DECK_NAME)
            deckViewModel.insert(Deck(deckName!!, "", ArrayList<Flashcard>()))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FlashcardsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DecksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}