package com.DevRAT.lessa.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.DevRAT.lessa.Database.Entities.Senas

@Dao
interface SenasDao {
    @Query("SELECT * from senas_table")
    fun getWord(): LiveData<List<Senas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(senas: Senas)

    @Update
    fun update(word: Senas)

    @Query("SELECT * from senas_table where categoria = :catergoria ")
    fun getCategoria(catergoria:String): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where favorito ")
    fun getFavoritos(): LiveData<List<Senas>>

}