package com.e_sea_Ruby.e_sea_language.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.DevRAT.lessa.Database.Entities.Category
import com.DevRAT.lessa.Database.Entities.Word
import com.DevRAT.lessa.Database.Repository.CategoryRepository
import com.DevRAT.lessa.Database.Repository.WordRepository
import com.example.myapplication.DataBase.WordDataBase
import kotlinx.coroutines.launch

class ViewModel (application: Application): AndroidViewModel(application) {
    private val wordRepository: WordRepository
    private val categoryRepository: CategoryRepository
    val allCategory: LiveData<List<Category>>
    val allWords: LiveData<List<Word>>
    val favoriteWords: LiveData<List<Word>>

    init {
        val categoryDao = WordDataBase.getDatabase(application, viewModelScope).categoryDao()
        val wordDao = WordDataBase.getDatabase(application, viewModelScope).wordDao()
        wordRepository = WordRepository(wordDao)
        categoryRepository = CategoryRepository(categoryDao)
        allCategory = categoryRepository.allCategory
        allWords = wordRepository.allWordPerCategory
        favoriteWords=wordRepository.allFavorite
    }

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }
}