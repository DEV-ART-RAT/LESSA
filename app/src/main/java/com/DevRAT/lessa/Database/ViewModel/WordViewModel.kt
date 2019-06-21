package com.DevRAT.lessa.Database.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.DevRAT.lessa.Database.Database.WordDataBase
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.Entities.Word
import com.DevRAT.lessa.Database.Repository.SenasRepository
import com.DevRAT.lessa.Database.Repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel (application: Application): AndroidViewModel(application) {
    private val wordRepository: WordRepository
    private val senasRepository: SenasRepository
    val allWords: LiveData<List<Word>>


    companion object {
        var allPalabras: LiveData<List<Senas>>? = null
    }


    init {
        val wordDao = WordDataBase.getDatabase(application, viewModelScope).wordDao()
        val senasDao = WordDataBase.getDatabase(application, viewModelScope).senasDao()
        wordRepository = WordRepository(wordDao)
        senasRepository = SenasRepository(senasDao)
        allWords = wordRepository.allWord


    }

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }

    fun callCategory (categoria: String) { allPalabras = senasRepository.allCategoria(categoria)}
}