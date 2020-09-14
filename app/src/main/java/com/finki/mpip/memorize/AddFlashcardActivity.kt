package com.finki.mpip.memorize

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddFlashcardActivity : AppCompatActivity() {

    private lateinit var editFrontView: EditText
    private lateinit var editBackView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_flashcard)

        editFrontView = findViewById(R.id.edit_front_flashcard)
        editBackView = findViewById(R.id.edit_back_flashcard)

        val buttonSave = findViewById<Button>(R.id.button_save)
        val buttonCancel = findViewById<Button>(R.id.button_cancel)
        buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editFrontView.text) || TextUtils.isEmpty(editBackView.text)) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val front = editFrontView.text.toString()
                replyIntent.putExtra(EXTRA_FRONT, front)
                val back = editBackView.text.toString()
                replyIntent.putExtra(EXTRA_BACK, back)
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
        const val EXTRA_FRONT = "com.finki.mpip.memorize.FRONT"
        const val EXTRA_BACK = "com.finki.mpip.memorize.BACK"
    }


}