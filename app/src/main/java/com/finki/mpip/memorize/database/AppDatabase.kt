package com.finki.mpip.memorize.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.finki.mpip.memorize.converters.FlashcardConverter
import com.finki.mpip.memorize.converters.UriConverters
import com.finki.mpip.memorize.daos.DeckDao
import com.finki.mpip.memorize.daos.FlashcardDao
import com.finki.mpip.memorize.daos.UserDao
import com.finki.mpip.memorize.model.Deck
import com.finki.mpip.memorize.model.Flashcard
import com.finki.mpip.memorize.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Flashcard::class, User::class, Deck::class), version = 2)
@TypeConverters(UriConverters::class, FlashcardConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun flashCardDao() : FlashcardDao

    abstract fun userDao(): UserDao

    abstract fun deckDao(): DeckDao

    //object Instance

//    companion object : SingletonHolder<AppDatabase, Context>({
//        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "memorize.db").build()
//    })

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "word_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(FlashcardDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class FlashcardDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.flashCardDao())
                }
            }
        }

        suspend fun populateDatabase(flashcardDao: FlashcardDao) {
            // Delete all content here.
            flashcardDao.deleteAll()

            // Add sample words.
            var flashcard = Flashcard("Sample flashcard 1", "Sample back of flashcard 1", 0)
            flashcardDao.addFlashcard(flashcard)
            flashcard = Flashcard("Sample flashcard 2", "Sample back of flashcard 2", 0)
            flashcardDao.addFlashcard(flashcard)

            // TODO: Add your own words!
        }
    }
}