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

            var word = Word("SALUDOS", "saludo", R.drawable.five)
            wordDao.insert(word)

            word = Word(  "MESES Y DIAS", "calendario", R.drawable.calendar)
            wordDao.insert(word)

            word = Word(  "COLORES", "color", R.drawable.rgb)
            wordDao.insert(word)

            word = Word( "COMIDAS", "comida", R.drawable.dish)
            wordDao.insert(word)

            word = Word(  "NUMEROS", "numero",R.drawable.number)
            wordDao.insert(word)

            word = Word( "VESTUARIO Y ACCESORIOS", "ropa", R.drawable.laundry)
            wordDao.insert(word)

            word = Word( "FAMILIA", "familia", R.drawable.family)
            wordDao.insert(word)

            word = Word( "VERBOS", "verbos", R.drawable.running)
            wordDao.insert(word)

            word = Word( "OBJETOS COMUNES", "comunes", R.drawable.desk)
            wordDao.insert(word)

            word = Word(  "ESTADOS CIVILES", "estados",R.drawable.rings)
            wordDao.insert(word)

            word = Word(  "OFICIOS", "oficios", R.drawable.farmer)
            wordDao.insert(word)

            word = Word(  "PROFESIONES", "profeciones", R.drawable.doctor)
            wordDao.insert(word)
        }


        suspend fun populateSenasDatabase(senasDao: SenasDao){

            //SALUDOS

            var senas = Senas(  "Hola",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenos dias",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenas noches",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenas tardes",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "¿Como estas?",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Bien",R.drawable.abc_cab_background_top_material,"saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mal",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "¿Como te llamas?",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mi nombre es",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mucho gusto",R.drawable.abc_cab_background_top_material, "saludo",false)
            senasDao.insert(senas)

            //CALENDARIO

            senas = Senas(  "Enero",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Febrero",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Marzo",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Abril",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Mayo",R.drawable.abc_cab_background_top_material,"calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Junio",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Julio",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Agosto",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Septiembre",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Octubre",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Noviembre",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Diciembre",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Lunes",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Martes",R.drawable.abc_cab_background_top_material,"calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Miercoles",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Jueves",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Viernes",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Sabado",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Domingo",R.drawable.abc_cab_background_top_material, "calendario",false)
            senasDao.insert(senas)

            //COLORES

            senas = Senas(  "Rojo",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Negro",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Blanco",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Amarillo",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Azul",R.drawable.abc_cab_background_top_material,"color",false)
            senasDao.insert(senas)

            senas = Senas(  "Celeste",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Rosado",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Verde",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Morado",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Gris",R.drawable.abc_cab_background_top_material, "color",false)
            senasDao.insert(senas)

            //COMIDAS

            senas = Senas(  "Pupusas",R.drawable.abc_cab_background_top_material, "comida" ,false)
            senasDao.insert(senas)

            senas = Senas(  "Pizza",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Carne",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Hamburguesa",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Salsa",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Curtidos",R.drawable.abc_cab_background_top_material,"comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Arroz",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Frijoles",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Platano",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Cafe",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Soda",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Agua",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Fresco",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pan dulce",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pan frances",R.drawable.abc_cab_background_top_material,"comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Macarrones",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pollo",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Ensalada",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pastel",R.drawable.abc_cab_background_top_material, "comida",false)
            senasDao.insert(senas)


            //VERBOS

            senas = Senas(  "Jugar",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Comer",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Dormir",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Estudiar",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Trabajar",R.drawable.abc_cab_background_top_material,"verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Manejar",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Tomar",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Cambiar",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Escribir",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Leer",R.drawable.abc_cab_background_top_material, "verbos",false)
            senasDao.insert(senas)

            //ESTADOS

            senas = Senas(  "Casado/a",R.drawable.abc_cab_background_top_material, "estados",false)
            senasDao.insert(senas)

            senas = Senas(  "Divorciado/a",R.drawable.abc_cab_background_top_material, "estados",false)
            senasDao.insert(senas)

            senas = Senas( "Viudo/a",R.drawable.abc_cab_background_top_material, "estados",false)
            senasDao.insert(senas)

            senas = Senas(  "Soltero/a",R.drawable.abc_cab_background_top_material, "estados",false)
            senasDao.insert(senas)

            senas = Senas(  "Acompañado/a",R.drawable.abc_cab_background_top_material, "estados",false)
            senasDao.insert(senas)

            //OFICIOS

            senas = Senas(  "Panadero/a",R.drawable.abc_cab_background_top_material, "oficios",false)
            senasDao.insert(senas)

            senas = Senas(  "Carpintero/a",R.drawable.abc_cab_background_top_material, "oficios",false)
            senasDao.insert(senas)

            senas = Senas(  "Soldador/a",R.drawable.abc_cab_background_top_material, "oficios",false)
            senasDao.insert(senas)

            senas = Senas(  "Agricultor/a",R.drawable.abc_cab_background_top_material, "oficios",false)
            senasDao.insert(senas)

            senas = Senas(  "Motorista",R.drawable.abc_cab_background_top_material, "oficios",false)
            senasDao.insert(senas)

            //PROFECIONES

            senas = Senas(  "Profesor/a",R.drawable.abc_cab_background_top_material, "profeciones",false)
            senasDao.insert(senas)

            senas = Senas(  "Doctor/a",R.drawable.abc_cab_background_top_material, "profeciones",false)
            senasDao.insert(senas)

            senas = Senas(  "Enfermero/a",R.drawable.abc_cab_background_top_material, "profeciones",false)
            senasDao.insert(senas)

            senas = Senas(  "Secretario/a",R.drawable.abc_cab_background_top_material, "profeciones",false)
            senasDao.insert(senas)

            senas = Senas(  "Ingeniero/a",R.drawable.abc_cab_background_top_material, "profeciones",false)
            senasDao.insert(senas)

            //COMUNES

            senas = Senas(  "Mesa",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Silla",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Televisor",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cama",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Computadora",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Telefono",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cartera",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Carro",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Bus",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Lapiz",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Lapicero",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Agenda",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuaderno",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Libro",R.drawable.abc_cab_background_top_material, "comunes",false)
            senasDao.insert(senas)


            //FAMILIA

            senas = Senas(  "Mamá",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Papá",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Abuela",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Abuelo",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hermano",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hermana",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hijo",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas( "Hija", R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Prima",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Primo",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Tío",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Tía",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuñado",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuñada",R.drawable.abc_cab_background_top_material, "familia",false)
            senasDao.insert(senas)

            //ROPA

            senas = Senas(  "Camisa",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Pantalón",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Short",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Blusa",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Vestido",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Calcetines",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Zapatos",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Cincho",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Collar",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Aritos",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Lentes",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Pulsera",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Reloj",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Camiseta",R.drawable.abc_cab_background_top_material, "ropa",false)
            senasDao.insert(senas)

            //NUMEROS

            senas = Senas(  "Uno (1)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Dos (2)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Tres (3)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuatro (4)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Cinco (5)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Seis (6)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Siete (7)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Ocho (8)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Nueve (9)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Diez (10)",R.drawable.abc_cab_background_top_material, "numero",false)
            senasDao.insert(senas)

        }
    }


}