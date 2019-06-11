package com.e_sea_Ruby.e_sea_language.Repository

import androidx.lifecycle.LiveData
import com.e_sea_Ruby.e_sea_language.Entities.Word
import com.example.myapplication.DAO.WordDao

class WordRepository (private val wordDao: WordDao) {

    val allWord: LiveData<List<Word>> = wordDao.getWord()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun update(word: Word){
        wordDao.update(word)
    }
}