package com.DevRAT.lessa.Database.Repository

import androidx.annotation.WorkerThread
import com.DevRAT.lessa.Database.DAO.SenaUserDao
import com.DevRAT.lessa.Database.Entities.SenaUser

class SenaUserRepository (private val senaUserDao: SenaUserDao) {

    @WorkerThread
    suspend fun getFavorito() = senaUserDao.getUser()

    suspend fun insert(senaUser: SenaUser) {
        senaUserDao.insert(senaUser)
    }

    suspend fun delete(senaUser: SenaUser) {
        senaUserDao.delete(senaUser)
    }
}