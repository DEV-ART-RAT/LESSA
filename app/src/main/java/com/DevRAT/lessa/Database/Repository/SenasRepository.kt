package com.DevRAT.lessa.Database.Repository

import androidx.lifecycle.LiveData
import com.DevRAT.lessa.Database.DAO.SenasDao
import com.DevRAT.lessa.Database.Entities.Senas

class SenasRepository (private val senasDao: SenasDao) {

    val allFavoritos: LiveData<List<Senas>> = senasDao.getFavoritos()

    fun allCategoria(catergoria:String): LiveData<List<Senas>> = senasDao.getCategoria( catergoria)

    /*fun setFavorite(favorite : Boolean, palabra :String)= senasDao.upateFavorito(favorite, palabra)*/

    suspend fun insert(senas: Senas) {
        senasDao.insert(senas)
    }

    suspend fun update(senas: Senas){
        senasDao.update(senas)
    }
}