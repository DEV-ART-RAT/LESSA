package com.DevRAT.lessa.Database.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.DevRAT.lessa.Database.DAO.SenasDao
import com.DevRAT.lessa.Database.DAO.WordDao
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.Entities.Word
import com.DevRAT.lessa.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Word::class, Senas::class], version = 1)
abstract class WordDataBase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun senasDao(): SenasDao

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
                        populateDatabase(database.wordDao())
                        populateSenasDatabase(database.senasDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {

            var word = Word( 0, "SALUDOS", "saludo", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "MESES Y DIAS", "calendario", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "COLORES", "color", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "COMIDAS", "comida", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "NUMEROS", "numero",R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "ROPA", "ropa", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "FAMILIA", "familia", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "VERBOS", "verbos", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "OBJETOS COMUNES", "comunes", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "ESTADOS CIVILES", "estados",R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "OFICIOS", "oficios", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)

            word = Word( 0, "PROFESIONES", "profeciones", R.drawable.abc_ab_share_pack_mtrl_alpha)
            wordDao.insert(word)
        }


        suspend fun populateSenasDatabase(senasDao: SenasDao){
            var senas = Senas( 0, "Hola",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "Buenos dias",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "Buenas noches",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "Buenas tardes",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "¿Como estas?",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "Bien",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "Mal",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "¿Como te llamas?",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "Mi nombre es",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)

            senas = Senas( 0, "Mucho gusto",R.drawable.abc_cab_background_top_material, true, false, false, false, false, false, false, false, false, false, false, false)
            senasDao.insert(senas)
        }
    }


}