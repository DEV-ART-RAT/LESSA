package com.DevRAT.lessa.Database.Repository

import androidx.lifecycle.LiveData
import com.DevRAT.lessa.Database.DAO.CategoryDao
import com.DevRAT.lessa.Database.Entities.Category


class CategoryRepository (private val categoryDao : CategoryDao) {

    val allCategory: LiveData<List<Category>> = categoryDao.getCategory()


    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun update(category: Category){
        categoryDao.update(category)
    }
}