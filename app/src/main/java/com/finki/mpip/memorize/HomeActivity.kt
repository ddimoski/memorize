package com.finki.mpip.memorize

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.finki.mpip.memorize.domain.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class HomeActivity : AppCompatActivity() {

    lateinit var loggedInUser: User
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var signOutButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        signOutButton = findViewById(R.id.btn_sign_out)

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
            Toast.makeText(this, String.format("Logged in: %s", loggedInUser), Toast.LENGTH_LONG).show()
        }
    }

    fun signOutWithGoogle(view: View) {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener{task ->
                Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show()
                finish()
            }
    }
}