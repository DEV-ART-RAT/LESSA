package com.DevRAT.lessa.Database.Repository

import androidx.lifecycle.LiveData
import com.DevRAT.lessa.Database.DAO.WordDao
import com.DevRAT.lessa.Database.Entities.Word

class WordRepository (private val wordDao: WordDao) {

    val allWord: LiveData<List<Word>> = wordDao.getWord()

    val allFavorite: LiveData<List<Word>> = wordDao.getFavorite()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun update(word: Word){
        wordDao.update(word)
    }
}