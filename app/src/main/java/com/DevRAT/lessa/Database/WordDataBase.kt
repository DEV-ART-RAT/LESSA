package com.example.myapplication.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.DevRAT.lessa.Database.DAO.CategoryDao
import com.DevRAT.lessa.Database.DAO.WordDao
import com.DevRAT.lessa.Database.Entities.Word
import com.DevRAT.lessa.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Word::class], version = 1)
abstract class WordDataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: WordDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordDataBase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDataBase::class.java,
                    "word_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch {
                        //populateDatabase(database.wordDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {


            var word = Word( 0, "SALUDOS", "saludo", 1,true,true)
            wordDao.insert(word)

            word = Word( 0, "MESES Y DIAS", "calendario", 1, false,false)
            wordDao.insert(word)

            word = Word( 0, "COLORES", "color", 1,true,true)
            wordDao.insert(word)

            word = Word( 0, "COMIDAS", "comida", 1, false,false)
            wordDao.insert(word)

            word = Word( 0, "NUMEROS", "numero", 1,true,true)
            wordDao.insert(word)

            word = Word( 0, "ROPA", "ropa", 1, false,false)
            wordDao.insert(word)

            word = Word( 0, "FAMILIA", "familia", 1,true,true)
            wordDao.insert(word)

            word = Word( 0, "VERBOS", "categoria",1, false,false)
            wordDao.insert(word)

            word = Word( 0, "OBJETOS COMUNES", "comunes", 1, false,false)
            wordDao.insert(word)

            word = Word( 0, "ESTADOS CIVILES", "estados", 1,true,true)
            wordDao.insert(word)

            word = Word( 0, "OFICIOS", "oficios", 1, false,false)
            wordDao.insert(word)

            word = Word( 0, "PROFESIONES", "profeciones", 1, false,false)
            wordDao.insert(word)
        }
    }


}