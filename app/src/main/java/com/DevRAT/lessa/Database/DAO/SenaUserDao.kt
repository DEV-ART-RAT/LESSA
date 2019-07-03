package com.DevRAT.lessa.Database.DAO

import androidx.room.*
import com.DevRAT.lessa.Database.Entities.SenaUser


@Dao
interface SenaUserDao {

    @Query("SELECT * from user_table")
    suspend fun getUser(): List<SenaUser>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(senaUser: SenaUser)

    @Delete
    suspend fun delete(senaUser: SenaUser)

}