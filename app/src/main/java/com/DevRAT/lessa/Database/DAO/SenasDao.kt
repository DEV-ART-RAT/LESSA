package com.DevRAT.lessa.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.DevRAT.lessa.Database.Entities.Senas

@Dao
interface SenasDao {

    @Query("SELECT * from senas_table where favorito = :flag")
    fun getFavoritos(flag : Boolean): LiveData<List<Senas>>

    @Query("SELECT * from senas_table order by palabra ASC" )
    fun getTodo(): LiveData<List<Senas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(senas: Senas)

    @Query("SELECT * from senas_table where categoria = :catergoria")
    fun getCategoria(catergoria:String): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where palabra = :sena ")
    fun getSena(sena:String): LiveData<Senas>

    @Query("UPDATE senas_table set favorito = :favorito")
    fun upateFavorito(favorito:Boolean)
    @Query("select * from senas_table where palabra like :name order by palabra")
    suspend fun searchSenaByName(name: String): List<Senas>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update (senas: Senas)

}