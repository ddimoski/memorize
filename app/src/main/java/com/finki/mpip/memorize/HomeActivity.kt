package com.finki.mpip.memorize

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.finki.mpip.memorize.database.AppDatabase
import com.finki.mpip.memorize.model.Deck
import com.finki.mpip.memorize.model.Flashcard
import com.finki.mpip.memorize.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {

    lateinit var loggedInUser: User
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var signOutButton: View
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val sharedPreferences = getSharedPreferences("com.finki.mpip.memorize", Context.MODE_PRIVATE)

        signOutButton = findViewById(R.id.btn_sign_out)
        bottomNavigation = findViewById(R.id.bottom_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navigationController = navHostFragment.navController

        bottomNavigation.setupWithNavController(navigationController)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            val personName = account.displayName
            val personGivenName = account.givenName
            val personFamilyName = account.familyName
            val personEmail = account.email
            val personId = account.id
            val personPhoto: Uri? = account.photoUrl

            loggedInUser = User(personId!!, personName!!, personGivenName.plus(" ").plus(personFamilyName), personEmail!!, personPhoto!!)
            //Toast.makeText(this, String.format("Logged in: %s", loggedInUser), Toast.LENGTH_LONG).show()
            sharedPreferences.edit().putString("userId", loggedInUser.id).apply()
        }

    }

    fun signOutWithGoogle(view: View) {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener{task ->
                Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show()
                finish()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    /*fun createSampleData(): List<Flashcard> {
        db.userDao().addUser(loggedInUser)
        val deck = Deck("SampleDeck", loggedInUser.id)
        deck.addFlashcard(Flashcard("SampleQuestion 1", "SampleAnswer 1", deck.id))
        deck.addFlashcard(Flashcard("SampleQuestion 2", "SampleAnswer 2", deck.id))
        deck.addFlashcard(Flashcard("SampleQuestion 3", "SampleAnswer 3", deck.id))
        deck.addFlashcard(Flashcard("SampleQuestion 4", "SampleAnswer 4", deck.id))
        db.deckDao().addDeck(deck)
        return db.flashCardDao().getFlashcardsByDeckId(deck.id)
    }*/
}