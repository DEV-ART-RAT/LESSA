package com.DevRAT.lessa.Database.ViewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.DevRAT.lessa.Database.Database.WordDataBase
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.Entities.Word
import com.DevRAT.lessa.Database.Repository.SenasRepository
import com.DevRAT.lessa.Database.Repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val wordRepository: WordRepository
    private val senasRepository: SenasRepository
    val allWords: LiveData<List<Word>>
    val allPalabras =  MutableLiveData<List<Senas>>()
    lateinit var alltodo :  LiveData<List<Senas>>
    val busca :  MutableLiveData<List<Senas>>
    //val allfavoritos: LiveData<List<Senas>>

    companion object{

        var allfavoritos : LiveData<List<Senas>>? = null
        var getSena: LiveData<Senas>? = null
    }

    fun updateSena(senas: Senas) = viewModelScope.launch {
        senasRepository.update(senas)
    }


    init {
        val wordDao = WordDataBase.getDatabase(application, viewModelScope).wordDao()
        val senasDao = WordDataBase.getDatabase(application, viewModelScope).senasDao()
        wordRepository = WordRepository(wordDao)
        senasRepository = SenasRepository(senasDao)
        allWords = wordRepository.allWord
        busca = MutableLiveData<List<Senas>>()




    }

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }

    fun callCategory(categoria: String) {
        viewModelScope.launch {
            allPalabras.value = senasRepository.allCategoria(categoria)
        }

    }

    fun callFavoritos() {
        allfavoritos = senasRepository.allFavoritos(true)
    }
    fun alltodo() {
       // busca = senasRepository.todaspalabras()
    }
    fun callSena (sena: String) { getSena = senasRepository.getSena(sena)}



    fun getSenaByNombre(name: String) {
        viewModelScope.launch {
            busca.value = senasRepository.getSenaByName(name)

        }
    }
 }