package com.DevRAT.lessa.Database.Repository

import androidx.annotation.WorkerThread
import com.DevRAT.lessa.Database.DAO.SenaUserDao
import com.DevRAT.lessa.Database.Entities.SenaUser
import com.DevRAT.lessa.UI.Activities.MainActivity

class SenaUserRepository (private val senaUserDao: SenaUserDao) {

    @WorkerThread
    suspend fun getFavorito(user: String) = senaUserDao.getUser(user)

    suspend fun insert(senaUser: SenaUser) {
        senaUserDao.insert(senaUser)
    }

    suspend fun delete(senaUser: SenaUser) {
        senaUserDao.delete(senaUser)
    }
}