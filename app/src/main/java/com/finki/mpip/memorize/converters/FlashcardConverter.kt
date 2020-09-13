package com.finki.mpip.memorize.converters

import android.net.Uri
import androidx.room.TypeConverter
import com.finki.mpip.memorize.model.Flashcard
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FlashcardConverter {
    @TypeConverter
    fun fromCountryLangList(value: List<Flashcard>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Flashcard>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<Flashcard> {
        val gson = Gson()
        val type = object : TypeToken<List<Flashcard>>() {}.type
        return gson.fromJson(value, type)
    }
}