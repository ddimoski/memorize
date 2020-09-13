package com.finki.mpip.memorize.database

import android.content.Context
import androidx.room.*
import com.finki.mpip.memorize.converters.FlashcardConverter
import com.finki.mpip.memorize.converters.UriConverters
import com.finki.mpip.memorize.daos.DeckDao
import com.finki.mpip.memorize.daos.FlashcardDao
import com.finki.mpip.memorize.daos.UserDao
import com.finki.mpip.memorize.helpers.SingletonHolder
import com.finki.mpip.memorize.model.Deck
import com.finki.mpip.memorize.model.Flashcard
import com.finki.mpip.memorize.model.User

@Database(entities = arrayOf(Flashcard::class, User::class, Deck::class), version = 1)
@TypeConverters(UriConverters::class, FlashcardConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flashCardDao() : FlashcardDao

    abstract fun userDao(): UserDao

    abstract fun deckDao(): DeckDao

    //object Instance

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "memorize.db").build()
    })
}