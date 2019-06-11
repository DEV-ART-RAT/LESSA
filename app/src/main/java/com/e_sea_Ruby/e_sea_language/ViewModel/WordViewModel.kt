package com.e_sea_Ruby.e_sea_language.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.e_sea_Ruby.e_sea_language.Entities.Word
import com.e_sea_Ruby.e_sea_language.Repository.WordRepository
import com.example.myapplication.DataBase.WordDataBase
import kotlinx.coroutines.launch

class WordViewModel (application: Application): AndroidViewModel(application) {
    private val wordRepository: WordRepository
    val allWords: LiveData<List<Word>>

    init {
        val wordDao = WordDataBase.getDatabase(application, viewModelScope).wordDao()
        wordRepository = WordRepository(wordDao)
        allWords = wordRepository.allWord
    }

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }
}