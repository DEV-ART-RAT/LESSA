package com.example.myapplication.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.e_sea_Ruby.e_sea_language.Entities.Word
import com.e_sea_Ruby.e_sea_language.R
import com.example.myapplication.DAO.WordDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Word::class], version = 1)
abstract class WordDataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao

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


            var word = Word( 0, "SALUDOS", "saludo", R.drawable.saludo,true,true)
            wordDao.insert(word)

            word = Word( 0, "MESES Y DIAS", "calendario", R.drawable.calendario, false,false)
            wordDao.insert(word)

            word = Word( 0, "COLORES", "color", R.drawable.colores,true,true)
            wordDao.insert(word)

            word = Word( 0, "COMIDAS", "comida", R.drawable.comida, false,false)
            wordDao.insert(word)

            word = Word( 0, "NUMEROS", "numero", R.drawable.numeros,true,true)
            wordDao.insert(word)

            word = Word( 0, "ROPA", "ropa", R.drawable.ropa, false,false)
            wordDao.insert(word)

            word = Word( 0, "FAMILIA", "familia", R.drawable.familia,true,true)
            wordDao.insert(word)

            word = Word( 0, "VERBOS", "categoria", R.drawable.verbos, false,false)
            wordDao.insert(word)

            word = Word( 0, "OBJETOS COMUNES", "comunes", R.drawable.comunes, false,false)
            wordDao.insert(word)

            word = Word( 0, "ESTADOS CIVILES", "estados", R.drawable.estadoCivil,true,true)
            wordDao.insert(word)

            word = Word( 0, "OFICIOS", "oficios", R.drawable.oficios, false,false)
            wordDao.insert(word)

            word = Word( 0, "PROFESIONES", "profeciones", R.drawable.profesiones, false,false)
            wordDao.insert(word)
        }
    }


}