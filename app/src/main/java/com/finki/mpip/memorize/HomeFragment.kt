package com.finki.mpip.memorize

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finki.mpip.memorize.adapters.FlashcardListAdapter
import com.finki.mpip.memorize.database.AppDatabase
import com.finki.mpip.memorize.model.Deck
import com.finki.mpip.memorize.model.Flashcard
import com.finki.mpip.memorize.model.User
import com.finki.mpip.memorize.view_model.FlashcardViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.doAsync

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val RC_NEW_FLASHCARD = 3

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var flashcardViewModel: FlashcardViewModel
    private lateinit var homeActivity: HomeActivity
    private lateinit var loggedInUser: User
    private lateinit var db: AppDatabase
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        homeActivity = HomeActivity()
        sharedPreferences = activity?.getSharedPreferences("com.finki.mpip.memorize", Context.MODE_PRIVATE)!!
        val userId = sharedPreferences?.getString("userId", "empty")
        if (userId != "empty") {
            doAsync{
                loggedInUser = db.userDao().getById(userId!!)
                Log.w("Success: ", "Async task done")}
        }
        //db = AppDatabase.getInstance(activity!!.applicationContext)
        //loggedInUser = homeActivity.loggedInUser

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View? = inflater.inflate(R.layout.fragment_home, container, false)
        val fab = view?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener {
            val intent = Intent(activity, AddFlashcardActivity::class.java)
            startActivityForResult(intent, RC_NEW_FLASHCARD)
        }
        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /*private fun createSampleData(): LiveData<List<Flashcard>> {
        //db.userDao().addUser(loggedInUser)
        val deck = Deck("SampleDeck", sharedPreferences.getString("userId", "empty")!!)
        deck.addFlashcard(Flashcard("SampleQuestion 1", "SampleAnswer 1", deck.id))
        deck.addFlashcard(Flashcard("SampleQuestion 2", "SampleAnswer 2", deck.id))
        deck.addFlashcard(Flashcard("SampleQuestion 3", "SampleAnswer 3", deck.id))
        deck.addFlashcard(Flashcard("SampleQuestion 4", "SampleAnswer 4", deck.id))
        db.deckDao().addDeck(deck)
        return db.flashCardDao().getFlashcardsByDeckId(deck.id)
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.flashcard_recyclerview)
        val adapter = FlashcardListAdapter(context!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        flashcardViewModel = ViewModelProvider(this).get(FlashcardViewModel::class.java)

        flashcardViewModel.allFlashcards.observe(viewLifecycleOwner, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setFlashcards(it) }
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