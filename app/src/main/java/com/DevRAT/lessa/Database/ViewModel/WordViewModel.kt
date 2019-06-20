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
    val allColor: LiveData<List<Senas>>
    val allComida: LiveData<List<Senas>>
    val allCalendario: LiveData<List<Senas>>
    val allComunes: LiveData<List<Senas>>
    val allOficios: LiveData<List<Senas>>
    val allProfeciones: LiveData<List<Senas>>
    val allNumeros: LiveData<List<Senas>>
    val allVerbos: LiveData<List<Senas>>
    val allEstados: LiveData<List<Senas>>
    val allFamilia: LiveData<List<Senas>>
    val allRopa: LiveData<List<Senas>>
    val allSaludo: LiveData<List<Senas>>

    init {
        val wordDao = WordDataBase.getDatabase(application, viewModelScope).wordDao()
        val senasDao = WordDataBase.getDatabase(application, viewModelScope).senasDao()
        wordRepository = WordRepository(wordDao)
        senasRepository = SenasRepository(senasDao)
        allWords = wordRepository.allWord
        allColor = senasRepository.allColor
        allComida = senasRepository.allComida
        allComunes = senasRepository.allComunes
        allCalendario= senasRepository.allCalendario
        allEstados = senasRepository.allEstados
        allFamilia = senasRepository.allFamilia
        allNumeros = senasRepository.allNumeros
        allOficios = senasRepository.allOficios
        allProfeciones = senasRepository.allProfeciones
        allVerbos = senasRepository.allVerbos
        allRopa = senasRepository.allRopa
        allSaludo = senasRepository.allSaludos

    }

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }
}