package com.example.myapplication.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.e_sea_Ruby.e_sea_language.Entities.Word

@Dao
interface WordDao {
    @Query("SELECT * from word_table")
    fun getWord(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Update
    fun update(word: Word)

}