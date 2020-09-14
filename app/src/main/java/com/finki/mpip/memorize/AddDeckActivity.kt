package com.finki.mpip.memorize

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddDeckActivity : AppCompatActivity() {

    private lateinit var editDeckName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_deck)

        editDeckName = findViewById(R.id.edit_deck_name)

        val buttonSave = findViewById<Button>(R.id.button_save_deck)
        val buttonCancel = findViewById<Button>(R.id.button_cancel_deck)
        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editDeckName.text)) {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_deck_name,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val front = editDeckName.text.toString()
                replyIntent.putExtra(EXTRA_DECK_NAME, front)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }

        buttonCancel.setOnClickListener{
            val replyIntent = Intent()
            setResult(Activity.RESULT_CANCELED, replyIntent)
            finish()
        }
    }

    companion object {
        const val EXTRA_DECK_NAME = "com.finki.mpip.memorize.DECK_NAME"
    }

}