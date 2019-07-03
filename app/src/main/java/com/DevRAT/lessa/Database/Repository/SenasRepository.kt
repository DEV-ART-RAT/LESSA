package com.DevRAT.lessa.Database.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.DevRAT.lessa.Database.DAO.SenasDao
import com.DevRAT.lessa.Database.Entities.Senas

class SenasRepository (private val senasDao: SenasDao) {

    fun allFavoritos (flag : Boolean) : LiveData<List<Senas>> = senasDao.getFavoritos(flag)

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