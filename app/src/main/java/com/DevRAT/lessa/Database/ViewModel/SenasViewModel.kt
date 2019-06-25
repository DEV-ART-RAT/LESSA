package com.DevRAT.lessa.Database.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.DevRAT.lessa.Database.Database.WordDataBase
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.Repository.SenasRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SenasViewModel (app: Application) : AndroidViewModel(app) {

    private val senaDao = WordDataBase.getDatabase(app,viewModelScope).senasDao()
    private val repository = SenasRepository(senaDao)

    companion object{
        var senass : LiveData<List<Senas>>? = null
    }
    //var cJob: Job? = null

    /*fun fetchMovie(name: String) {
        cJob?.cancel()
        cJob = viewModelScope.launch {
            val result = repository.movieSearch(name)
            movieList.value = result
            writeToDb(result)
        }
    }*/

    /*private suspend fun writeToDb(data: List<Movie>) {
        data.forEach {
            it.date = Date().toString()
            repository.insert(it)
        }
    }*/

    fun load() {

            var list = repository.allFavoritos(true)
            Log.d("com.DevRAT.lesa",list.toString())
            senass = list

    }


}