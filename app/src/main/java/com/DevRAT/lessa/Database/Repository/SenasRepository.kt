package com.DevRAT.lessa.Database.Repository

import androidx.lifecycle.LiveData
import com.DevRAT.lessa.Database.DAO.SenasDao
import com.DevRAT.lessa.Database.Entities.Senas

class SenasRepository (private val senasDao: SenasDao) {

    val allColor: LiveData<List<Senas>> = senasDao.getColor()

    val allComida: LiveData<List<Senas>> = senasDao.getComida()

    val allComunes: LiveData<List<Senas>> = senasDao.getComunes()

    val allEstados: LiveData<List<Senas>> = senasDao.getEstados()

    val allFamilia: LiveData<List<Senas>> = senasDao.getFamilia()

    val allNumeros: LiveData<List<Senas>> = senasDao.getNumeros()

    val allOficios: LiveData<List<Senas>> = senasDao.getOficios()

    val allProfeciones: LiveData<List<Senas>> = senasDao.getProfeciones()

    val allSaludos: LiveData<List<Senas>> = senasDao.getSaludos()

    val allCalendario: LiveData<List<Senas>> = senasDao.getCalendario()

    suspend fun insert(senas: Senas) {
        senasDao.insert(senas)
    }

    suspend fun update(senas: Senas){
        senasDao.update(senas)
    }
}