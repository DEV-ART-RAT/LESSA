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


@Database(entities = [Word::class, Senas::class], version = 2)
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

            word = Word( "VERBOS", "verbos", R.drawable.icon_running)
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

            senas = Senas(  "Buenos dias","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_buenosdias.gif?alt=media&token=37e6d61b-554e-4ef1-be95-1885c9e742fc", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenas noches","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_buenasnoches.gif?alt=media&token=27861584-aece-4758-b8b9-3b3dbc5f4dd0", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Buenas tardes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_buenastardes.gif?alt=media&token=86613a2b-7585-43fa-b0c6-8520aa5017f5", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "¿Como estas?","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_comoestas.gif?alt=media&token=aa39c125-9087-4846-8afa-24e1d3c36614", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Bien","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_bien.gif?alt=media&token=6942898d-9f0d-4d6a-b9aa-24f108579cbe","saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mal","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_mal.gif?alt=media&token=cdeb2bc7-bc45-4fb0-a37f-2d6889922c42", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "¿Como te llamas?","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cualestunombre.gif?alt=media&token=e6e7b083-80f0-43a6-b40e-9026d4aa2ac3", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mi nombre es","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_minombrees.gif?alt=media&token=418a7aed-b623-4a0b-bc25-750727315891", "saludo",false)
            senasDao.insert(senas)

            senas = Senas(  "Mucho gusto","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_muchogusto.gif?alt=media&token=ab0c2f4a-75b8-4fc3-934b-dd6777bbf64e", "saludo",false)
            senasDao.insert(senas)

            //CALENDARIO

            senas = Senas(  "Lunes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_lunes.gif?alt=media&token=88438f40-565e-45b8-8084-b4bc5ec00c18", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Martes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_martes.gif?alt=media&token=d664432c-cfa7-44c4-bf2f-615595d264a9","calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Miercoles","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_miercoles.gif?alt=media&token=9c76c096-7347-4c0b-b32c-7e21900f25d5", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Jueves","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_jueves.gif?alt=media&token=f3a2bca6-8bcb-4ecd-9d28-5efcdbe9cba6", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Viernes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_viernes.gif?alt=media&token=ebe13d7f-35b2-4535-9a9d-3ed570d2c8b1", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Sabado","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_sabado.gif?alt=media&token=58f26cf3-cd06-41e4-9b44-a28844ca34c8", "calendario",false)
            senasDao.insert(senas)

            senas = Senas(  "Domingo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_domingo.gif?alt=media&token=0693041b-4158-483b-9dbc-a41c58e6b852", "calendario",false)
            senasDao.insert(senas)

            //COLORES

            senas = Senas(  "Rojo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_rojo.gif?alt=media&token=2e76eaca-b806-41b2-b250-1ed4607f3b00", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Negro","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_negro.gif?alt=media&token=81bbc14a-0635-4cfa-91f1-12e4403f6fc5", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Blanco","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_blanco.gif?alt=media&token=033e73b8-377e-46b8-ae02-16215dc5058d", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Azul","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_azul.gif?alt=media&token=4dc5da40-2545-46e9-82b3-895357ede0cf","color",false)
            senasDao.insert(senas)

            senas = Senas(  "Celeste","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_celeste.gif?alt=media&token=094d44ac-3651-49b4-aec8-818ae90ad2b3", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Rosado","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_rosado.gif?alt=media&token=0e7549a9-e451-4960-b269-31644f37433e", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Verde","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_verde.gif?alt=media&token=2bacef95-ac7e-40db-b3b5-5207d9fb4d5f", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Morado","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_morado.gif?alt=media&token=f9135fec-0fb3-42f9-b995-b1ded6e07655", "color",false)
            senasDao.insert(senas)

            senas = Senas(  "Gris","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_gris.gif?alt=media&token=05db5653-3e59-4ba0-8fb1-56b7a3eff1b0", "color",false)
            senasDao.insert(senas)

            //COMIDAS

            senas = Senas(  "Pupusas","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_pupusas.gif?alt=media&token=b154b905-c425-4587-83af-9ba1e530817d", "comida" ,false)
            senasDao.insert(senas)

            senas = Senas(  "Pizza","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_pizza.gif?alt=media&token=28d297b5-04cd-4866-ad21-dd2fd96765ac", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Carne","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_carne.gif?alt=media&token=be5e1284-3f18-4796-8b6c-190a24c51071", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Hamburguesa","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hamburguesa.gif?alt=media&token=b261440e-f0ad-4fd5-a5fa-b11aa46453b9", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Arroz","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_arroz.gif?alt=media&token=f60b41ed-b336-441e-aa5b-a6b62ea89511", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Frijoles","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_frijol.gif?alt=media&token=cf5cf391-9d12-4f01-8fb1-563076245b8c", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Platano","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_platano.gif?alt=media&token=71e27b36-fa5c-489c-957c-e5eff6f4f859", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Cafe","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cafe.gif?alt=media&token=26ae0515-5708-4f35-8b7a-dcda4aae32b1", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Soda","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_soda.gif?alt=media&token=d7184ccb-97bd-4749-9959-03ddb78f8294", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Agua","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_agua.gif?alt=media&token=a65a5bfd-35c8-4e00-ab30-ac3a929162c0", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Jugo de naranja","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_jugodenaranja.gif?alt=media&token=b085a447-1a0f-4ad5-a7c0-c8bfaa5aa1ac", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pan dulce","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_pandulce.gif?alt=media&token=0d605907-2e56-4681-8e4b-8f3862bf991b", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pan frances","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_panfrances.gif?alt=media&token=a0d1259c-02e9-4cba-a5c4-729a4800651a","comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pollo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_pollo.gif?alt=media&token=9360b6d0-0df9-4d72-8dd8-508af11d112a", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Pastel","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_pastel.gif?alt=media&token=d7bf93f0-1008-4210-b92b-f3b3e3ab6aa3", "comida",false)
            senasDao.insert(senas)

            senas = Senas(  "Sopa","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_sopa.gif?alt=media&token=db691ef3-be15-48ef-9ed0-d027ccd6541f", "comida",false)
            senasDao.insert(senas)


            //VERBOS

            senas = Senas(  "Jugar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_jugar.gif?alt=media&token=b2e0a49e-321a-491e-b3a6-54d6c1ac96ae", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Comer","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_comer.gif?alt=media&token=1929f42f-2e29-4a06-ad86-70adef08ed90", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Dormir","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_dormir.gif?alt=media&token=2b1b0836-3628-496d-93fa-014e3d62a8b3", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Estudiar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_estudiar.gif?alt=media&token=31500286-d173-490f-ae96-1752173fc4e9", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Trabajar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_trabajar.gif?alt=media&token=3d703d68-a129-41b4-9557-898f4d862ece","verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Caminar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_caminar.gif?alt=media&token=8790816f-469d-409c-be7b-9c7c005ea8a6","verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Manejar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_manejar.gif?alt=media&token=087f77d6-3830-4868-a590-ca7bcd2c0a0d", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Tomar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_tomar.gif?alt=media&token=a16908d4-e204-4b4e-8ba7-850f2743022e", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Escribir","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_escribir.gif?alt=media&token=be084573-d85b-458b-a438-cd30ba990a11", "verbos",false)
            senasDao.insert(senas)

            senas = Senas(  "Leer","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_leer.gif?alt=media&token=5f22830a-ef98-455d-bd9c-cd1f400c898d", "verbos",false)
            senasDao.insert(senas)


            //TRABAJOS


            senas = Senas(  "Carpintero/a","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_carpintero.gif?alt=media&token=291ab607-4d2d-4033-9494-e3f36cdebdab", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Motorista","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_motorista.gif?alt=media&token=50484a53-276c-4a81-9668-3df685bacd36", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Profesor/a","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_profesor.gif?alt=media&token=bbdcb565-af56-46c1-a594-a941f6cd2e7c", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Enfermero/a","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_enfermera.gif?alt=media&token=7da2b63c-2406-4deb-8fe7-a7f2cacff575", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Secretario/a","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_secretaria.gif?alt=media&token=d75ac29f-0bb7-48a6-af8a-c0b5d474d1ae", "trabajos",false)
            senasDao.insert(senas)

            senas = Senas(  "Policia","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_policia.gif?alt=media&token=b29bb95e-ec8e-4807-be79-85c13f8aed20", "trabajos",false)
            senasDao.insert(senas)


            //COMUNES

            senas = Senas(  "Mesa","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_mesa.gif?alt=media&token=3bf2a34c-aeb7-473c-866d-e44595d416f7", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Silla","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_silla.gif?alt=media&token=c8bb6a25-c194-47c0-b1e4-db5bb5197603", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Televisor","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_televisor.gif?alt=media&token=22d2fc33-f3d2-46f9-b69d-9ed5c4505b2c", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cama","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cama.gif?alt=media&token=156990fc-479d-49f4-af93-fab14dc6f185", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Computadora","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_computadora.gif?alt=media&token=a05262ce-f6b4-4cbb-b196-a9e3f4e92671", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Telefono","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_telefono.gif?alt=media&token=e2a1a81f-ea13-4ac2-93bf-e89f1665fa2b", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Celular","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_movil.gif?alt=media&token=77992533-e2e7-4d15-849c-bd4f9c632ae8", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Bus","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_bus.gif?alt=media&token=ce692c7b-1bf1-4bfb-9ad5-67be1ed5e5df", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Lapiz","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_lapiz.gif?alt=media&token=a0ab2b64-460d-42e1-bba5-bcff8d5a612e", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Lapicero","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_lapicero.gif?alt=media&token=3890caa6-9fff-4c4d-bfbd-756632901f95", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Agenda","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_agenda.gif?alt=media&token=c1426b30-73d9-4473-8eef-e8192a368b70", "comunes",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuaderno","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cuaderno.gif?alt=media&token=4f4e64cd-7669-4297-be9a-98196d8f85d3", "comunes",false)
            senasDao.insert(senas)


            //FAMILIA

            senas = Senas(  "Mamá","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_mama.gif?alt=media&token=2444b0a2-4103-4d18-a6a5-aac7bc8d9877", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Papá","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_papa.gif?alt=media&token=051ad1f0-57ad-47b7-a62a-eba4b89758e3", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Abuela","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_abuela.gif?alt=media&token=97ccf565-375c-432c-ae00-496847b7849d", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Abuelo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_abuelo.gif?alt=media&token=24917f1e-e9d8-4640-b69a-06a8b0e9d05d", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hermano","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hermano.gif?alt=media&token=a72a9604-90d2-4d7f-abfc-2f25ea45315b", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hermana","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hermana.gif?alt=media&token=ac14366c-cdb1-4878-ad0d-d56ad491a17e", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Hijo","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hijo.gif?alt=media&token=c152527f-fa0b-4eec-aa46-e21dada6fa62", "familia",false)
            senasDao.insert(senas)

            senas = Senas( "Hija", "https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_hija.gif?alt=media&token=b785123d-55b1-4777-803d-9ea095125f00", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Tío","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_tio.gif?alt=media&token=702d1ade-242b-4c7a-afa6-727918371130", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Tía","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_tia.gif?alt=media&token=a775b88f-b3c4-4c57-a86f-48b3986e9a95", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuñado","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cunado.gif?alt=media&token=e70006d0-9910-4bf8-a5ab-6388a0eece50", "familia",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuñada","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cunada.gif?alt=media&token=59cbad3e-c05a-4daa-9b25-5e8543d07a14", "familia",false)
            senasDao.insert(senas)

            //ROPA

            senas = Senas(  "Camisa","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_camisa.gif?alt=media&token=9da9d76e-e6db-456e-82c5-5843f955bfbc", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Pantalón","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_pantalon.gif?alt=media&token=33df45cf-a3eb-442f-8c4d-482c448d7f7d", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Short","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_short.gif?alt=media&token=00aeddba-f056-4fed-92e8-fe92332d67bf", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Vestido","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_vestido.gif?alt=media&token=159b8770-6b0c-4942-8b89-bb60647b70bf", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Calcetines","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_calcetines.gif?alt=media&token=c8160c0b-3feb-4916-8a49-2a1e783a17b8", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Cincho","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cincho.gif?alt=media&token=67c546dc-3f12-4b9b-9cc9-050f8615d258", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Collar","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_collar.gif?alt=media&token=6a28957c-174e-4938-b6bd-83868538c9ba", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Aritos","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_arrito.gif?alt=media&token=8e6972e8-8f2a-4ed8-8518-a12d61f0c72e", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Lentes","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_lentes.gif?alt=media&token=18be40b2-c16e-4981-b876-751e2dde665a", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Pulsera","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_pulsera.gif?alt=media&token=34ea0d0d-1fb6-4dde-807c-ebf337be4d3c", "ropa",false)
            senasDao.insert(senas)

            senas = Senas(  "Reloj","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_relog.gif?alt=media&token=c460a611-4e7c-4abd-b54c-a69fc1f52ff1", "ropa",false)
            senasDao.insert(senas)

            //NUMEROS

            senas = Senas(  "Uno (1)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_uno.gif?alt=media&token=45ff44d4-cfa9-4964-8528-327524fd533d", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Dos (2)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_dos.gif?alt=media&token=7dccce54-0549-4c1f-808e-67fbadaf919a", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Tres (3)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_tres.gif?alt=media&token=2a232b60-4baa-47a7-8c58-ec8fe82f0997", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Cuatro (4)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cuatro.gif?alt=media&token=f5ec7942-77fa-42dc-acac-7a20a89372bd", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Cinco (5)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_cinco.gif?alt=media&token=8b3fae38-e141-4c7a-9de4-d5d6514bca81", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Seis (6)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_seis.gif?alt=media&token=d2a31916-f397-42c0-9e11-14b8f6c96968", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Siete (7)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_siete.gif?alt=media&token=cb4d4d56-ce04-4159-8c89-4c17ae8d80a3", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Ocho (8)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_ocho.gif?alt=media&token=ca604df9-acd3-4304-9510-8e0639dffbbb", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Nueve (9)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_nueve.gif?alt=media&token=719919b2-7696-47be-883a-6323fa02b1ca", "numero",false)
            senasDao.insert(senas)

            senas = Senas(  "Diez (10)","https://firebasestorage.googleapis.com/v0/b/lessa-c8cf6.appspot.com/o/senas%2Fgif_diez.gif?alt=media&token=426f8bd5-76ef-4f9d-8682-8da4791a1792", "numero",false)
            senasDao.insert(senas)

        }
    }


}