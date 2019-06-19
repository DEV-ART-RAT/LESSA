package com.DevRAT.lessa.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.DevRAT.lessa.Database.Entities.Word

@Dao
interface WordDao {
    @Query("SELECT * from word_table")
    fun getWord(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update
    fun update(word: Word)

    @Query("SELECT * from word_table where favorito ")
    fun getFavorite(): LiveData<List<Word>>
}