package com.DevRAT.lessa.Database.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.DevRAT.lessa.Database.DAO.SenasDao
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.UI.Activities.MainActivity

class SenasRepository (private val senasDao: SenasDao) {

    @WorkerThread
    suspend fun allFavoritos (user : String) : List<Senas> = senasDao.getFavoritos(user)

    @WorkerThread
    suspend fun allCategoria(catergoria:String): List<Senas> = senasDao.getCategoria(catergoria)


    fun todaspalabras(): LiveData<List<Senas>> = senasDao.getTodo()

    /*fun setFavorite(favorite : Boolean, palabra :String)= senasDao.upateFavorito(favorite, palabra)*/

    suspend fun insert(senas: Senas) {
        senasDao.insert(senas)
    }

    fun getSena(sena: String): LiveData<Senas> = senasDao.getSena(sena)

    suspend fun update(senas: Senas){
        senasDao.update(senas)
    }

    @WorkerThread
    suspend fun getSenaByName(name: String) = senasDao.searchSenaByName(name)
}