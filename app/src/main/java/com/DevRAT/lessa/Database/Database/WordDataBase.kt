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

            var senas = Senas(  "Hola",R.drawable.gif_hola, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenos dias",R.drawable.gif_buenosdias, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenas noches",R.drawable.gif_buenasnoches, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenas tardes",R.drawable.gif_buenastardes, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "¿Como estas?",R.drawable.gif_comoestas, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Bien",R.drawable.gif_bien,"saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mal",R.drawable.gif_mal, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "¿Como te llamas?",R.drawable.gif_cualestunombre, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mi nombre es",R.drawable.gif_nombre, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mucho gusto",R.drawable.gif_muchogusto, "saludo",false)
            senasDao.insert(senas)

            //CALENDARIO

            senas = Senas(  "Lunes",R.drawable.gif_lunes, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Martes",R.drawable.gif_martes,"calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Miercoles",R.drawable.gif_miercoles, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Jueves",R.drawable.gif_jueves, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Viernes",R.drawable.gif_viernes, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Sabado",R.drawable.gif_sabado, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Domingo",R.drawable.gif_domingo, "calendario",false)
            senasDao.insert(senas)

            //COLORES

            senas = Senas(  "Rojo",R.drawable.gif_rojo, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Negro",R.drawable.gif_negro, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Blanco",R.drawable.gif_blanco, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Azul",R.drawable.gif_azul,"color",false)
            senasDao.insert(senas)

            senas = Senas(  "Celeste",R.drawable.gif_celeste, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Rosado",R.drawable.gif_rosado, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Verde",R.drawable.gif_verde, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Morado",R.drawable.gif_morado, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Gris",R.drawable.gif_gris, "color",false)
            senasDao.insert(senas)

            //COMIDAS

            senas = Senas(  "Pupusas",R.drawable.gif_pupusas, "comida" ,false)
            senasDao.insert(senas)

            senas = Senas(  "Pizza",R.drawable.gif_pizza, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Carne",R.drawable.gif_carne, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Hamburguesa",R.drawable.gif_hamburguesa, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Arroz",R.drawable.gif_arroz, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Frijoles",R.drawable.gif_frijol, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Platano",R.drawable.gif_platano, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Cafe",R.drawable.gif_cafe, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Soda",R.drawable.gif_soda, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Agua",R.drawable.gif_agua, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Jugo de naranja",R.drawable.gif_jugodenaranja, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pan dulce",R.drawable.gif_pandulce, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pan frances",R.drawable.gif_panfrances,"comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pollo",R.drawable.gif_pollo, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pastel",R.drawable.gif_pastel, "comida",false)
            senasDao.insert(senas)


            //VERBOS

            senas = Senas(  "Jugar",R.drawable.gif_jugar, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Comer",R.drawable.gif_comer, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Dormir",R.drawable.gif_dormir, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Estudiar",R.drawable.gif_estudiar, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Trabajar",R.drawable.gif_trabajar,"verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Manejar",R.drawable.gif_manejar, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Tomar",R.drawable.gif_tomar, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Escribir",R.drawable.gif_escribir, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Leer",R.drawable.gif_leer, "verbos",false)
            senasDao.insert(senas)


            //TRABAJOS


            senas = Senas(  "Carpintero/a",R.drawable.gif_carpintero, "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Motorista",R.drawable.gif_motorista, "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Profesor/a",R.drawable.gif_profesor, "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Enfermero/a",R.drawable.gif_enfermera, "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Secretario/a",R.drawable.gif_secretaria, "trabajos",false)
            senasDao.insert(senas)


            //COMUNES

            senas = Senas(  "Mesa",R.drawable.gif_mesa, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Silla",R.drawable.gif_silla, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Televisor",R.drawable.gif_televisor, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cama",R.drawable.gif_cama, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Computadora",R.drawable.gif_computadora, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Telefono",R.drawable.gif_telefono, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Celular",R.drawable.gif_movil, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Bus",R.drawable.gif_bus, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Lapiz",R.drawable.gif_lapiz, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Lapicero",R.drawable.gif_lapicero, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Agenda",R.drawable.gif_agenda, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuaderno",R.drawable.gif_cuaderno, "comunes",false)
            senasDao.insert(senas)


            //FAMILIA

            senas = Senas(  "Mamá",R.drawable.gif_mama, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Papá",R.drawable.gif_papa, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Abuela",R.drawable.gif_abuela, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Abuelo",R.drawable.gif_abuelo, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hermano",R.drawable.gif_hermano, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hermana",R.drawable.gif_hermana, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hijo",R.drawable.gif_hijo, "familia",false)
            senasDao.insert(senas)

            senas = Senas( "Hija", R.drawable.gif_hija, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Tío",R.drawable.gif_tio, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Tía",R.drawable.gif_tia, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuñado",R.drawable.gif_cunado, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuñada",R.drawable.gif_cunada, "familia",false)
            senasDao.insert(senas)

            //ROPA

            senas = Senas(  "Camisa",R.drawable.gif_camisa, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Pantalón",R.drawable.gif_pantalon, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Short",R.drawable.gif_short, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Vestido",R.drawable.gif_vestido, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Calcetines",R.drawable.gif_calcetines, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Cincho",R.drawable.gif_cincho, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Collar",R.drawable.gif_collar, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Aritos",R.drawable.gif_arrito, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Lentes",R.drawable.gif_lentes, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Pulsera",R.drawable.gif_pulsera, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Reloj",R.drawable.gif_relog, "ropa",false)
            senasDao.insert(senas)

            //NUMEROS

            senas = Senas(  "Uno (1)",R.drawable.gif_uno, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Dos (2)",R.drawable.gif_dos, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Tres (3)",R.drawable.gif_tres, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuatro (4)",R.drawable.gif_cuatro, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Cinco (5)",R.drawable.gif_cinco, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Seis (6)",R.drawable.gif_seis, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Siete (7)",R.drawable.gif_siete, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Ocho (8)",R.drawable.gif_ocho, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Nueve (9)",R.drawable.gif_nueve, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Diez (10)",R.drawable.gif_diez, "numero",false)
            senasDao.insert(senas)

        }
    }


}