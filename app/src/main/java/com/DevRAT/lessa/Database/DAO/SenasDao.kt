package com.DevRAT.lessa.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.DevRAT.lessa.Database.Entities.Senas

@Dao
interface SenasDao {

    @Query("SELECT * from senas_table where favorito = :flag")
    fun getFavoritos(flag : Boolean): LiveData<List<Senas>>

    @Query("SELECT * from senas_table")
    fun getWord(): LiveData<List<Senas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(senas: Senas)

    @Query("SELECT * from senas_table where categoria = :catergoria")
    fun getCategoria(catergoria:String): LiveData<List<Senas>>




    /*@Query("UPDATE senas_table set favorito = :favorito where palabra = :palabra")
    fun upateFavorito(favorito:Boolean,palabra : String): Boolean*/

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update (senas: Senas)

}