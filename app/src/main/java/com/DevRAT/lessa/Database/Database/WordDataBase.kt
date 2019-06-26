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
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.wordDao())
                        populateSenasDatabase(database.senasDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {

            var word = Word("SALUDOS", "saludo", R.drawable.icon_handshake)
            wordDao.insert(word)

            word = Word(  "MESES Y DIAS", "calendario", R.drawable.icon_calendar)
            wordDao.insert(word)

            word = Word(  "COLORES", "color", R.drawable.icon_rgb)
            wordDao.insert(word)

            word = Word( "COMIDAS", "comida", R.drawable.icon_diet)
            wordDao.insert(word)

            word = Word(  "NUMEROS", "numero",R.drawable.icon_number)
            wordDao.insert(word)

            word = Word( "VESTUARIO Y ACCESORIOS", "ropa", R.drawable.icon_laundry)
            wordDao.insert(word)

            word = Word( "FAMILIA", "familia", R.drawable.icon_family)
            wordDao.insert(word)

            word = Word( "VERBOS", "verbos", R.drawable.icon_rgb)
            wordDao.insert(word)

            word = Word( "OBJETOS COMUNES", "comunes", R.drawable.icon_desk)
            wordDao.insert(word)

            word = Word(  "TRABAJOS", "trabajos", R.drawable.icon_farmer)
            wordDao.insert(word)

        }


        suspend fun populateSenasDatabase(senasDao: SenasDao){

            //SALUDOS

            var senas = Senas(  "Hola","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenos dias","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenas noches","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenas tardes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "¿Como estas?","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Bien","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63","saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mal","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "¿Como te llamas?","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mi nombre es","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mucho gusto","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "saludo",false)
            senasDao.insert(senas)

            //CALENDARIO

            senas = Senas(  "Lunes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Martes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63","calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Miercoles","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Jueves","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Viernes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Sabado","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Domingo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "calendario",false)
            senasDao.insert(senas)

            //COLORES

            senas = Senas(  "Rojo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Negro","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Blanco","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Azul","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63","color",false)
            senasDao.insert(senas)

            senas = Senas(  "Celeste","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Rosado","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Verde","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Morado","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Gris","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "color",false)
            senasDao.insert(senas)

            //COMIDAS

            senas = Senas(  "Pupusas","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida" ,false)
            senasDao.insert(senas)

            senas = Senas(  "Pizza","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Carne","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Hamburguesa","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Arroz","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Frijoles","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Platano","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Cafe","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Soda","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Agua","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Jugo de naranja","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pan dulce","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pan frances","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63","comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pollo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pastel","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comida",false)
            senasDao.insert(senas)


            //VERBOS

            senas = Senas(  "Jugar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Comer","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Dormir","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Estudiar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Trabajar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63","verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Manejar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Tomar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Escribir","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Leer","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "verbos",false)
            senasDao.insert(senas)


            //TRABAJOS


            senas = Senas(  "Carpintero/a","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Motorista","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Profesor/a","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Enfermero/a","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Secretario/a","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "trabajos",false)
            senasDao.insert(senas)


            //COMUNES

            senas = Senas(  "Mesa","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Silla","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Televisor","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cama","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Computadora","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Telefono","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Celular","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Bus","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Lapiz","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Lapicero","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Agenda","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuaderno","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "comunes",false)
            senasDao.insert(senas)


            //FAMILIA

            senas = Senas(  "Mamá","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Papá","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Abuela","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Abuelo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hermano","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hermana","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hijo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas( "Hija", "https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Tío","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Tía","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuñado","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuñada","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "familia",false)
            senasDao.insert(senas)

            //ROPA

            senas = Senas(  "Camisa","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Pantalón","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Short","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Vestido","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Calcetines","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Cincho","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Collar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Aritos","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Lentes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Pulsera","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Reloj","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "ropa",false)
            senasDao.insert(senas)

            //NUMEROS

            senas = Senas(  "Uno (1)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Dos (2)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Tres (3)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuatro (4)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Cinco (5)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Seis (6)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Siete (7)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Ocho (8)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Nueve (9)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Diez (10)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hola.gif?alt=media&token=dcb56b97-9fed-4f4c-a074-a584df754b63", "numero",false)
            senasDao.insert(senas)

        }
    }


}