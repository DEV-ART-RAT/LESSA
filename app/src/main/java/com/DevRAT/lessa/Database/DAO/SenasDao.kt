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

    @Query("SELECT * from senas_table where color ")
    fun getColor(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where comida ")
    fun getComida(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where comunes ")
    fun getComunes(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where estados ")
    fun getEstados(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where familia ")
    fun getFamilia(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where numeros ")
    fun getNumeros(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where oficios ")
    fun getOficios(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where profeciones ")
    fun getProfeciones(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where ropa ")
    fun getRopa(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where verbos ")
    fun getVerbos(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where saludo ")
    fun getSaludos(): LiveData<List<Senas>>

    @Query("SELECT * from senas_table where calendario ")
    fun getCalendario(): LiveData<List<Senas>>


}