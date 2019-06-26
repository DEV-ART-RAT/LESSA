package com.DevRAT.lessa.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.DevRAT.lessa.Database.Entities.SenaUser


@Dao
interface SenaUserDao {
    @Query("SELECT * from user_table")
    fun getUser(): LiveData<List<SenaUser>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(senaUser: SenaUser)

}