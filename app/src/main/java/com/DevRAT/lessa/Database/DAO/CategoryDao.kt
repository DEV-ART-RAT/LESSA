package com.DevRAT.lessa.Database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.DevRAT.lessa.Database.Entities.Category

@Dao
interface CategoryDao {
    @Query("SELECT * from category_table")
    fun getCategory(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Update
    fun update(category: Category)

}