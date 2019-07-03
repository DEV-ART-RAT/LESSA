package com.DevRAT.lessa.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.DevRAT.lessa.Database.Entities.Senas

@Dao
interface SenasDao {

    @Query("SELECT * from senas_table a inner join user_table b on a.Palabra = b.palabra and b.usuario = :user ")
    suspend fun getFavoritos( user : String ): List<Senas>

    @Query("SELECT * from senas_table order by palabra ASC" )
    fun getTodo(): LiveData<List<Senas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(senas: Senas)

    @Update
    suspend fun insertUp(senas: Senas)

    @Query("SELECT * from senas_table where categoria = :catergoria")
    suspend fun getCategoria(catergoria:String): List<Senas>

    @Query("SELECT * from senas_table where palabra = :sena ")
    fun getSena(sena:String): LiveData<Senas>

    @Query("UPDATE senas_table set favorito = :favorito")
    fun upateFavorito(favorito:Boolean)

    @Query("select * from senas_table where palabra like :name order by palabra")
    suspend fun searchSenaByName(name: String): List<Senas>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update (senas: Senas)

}