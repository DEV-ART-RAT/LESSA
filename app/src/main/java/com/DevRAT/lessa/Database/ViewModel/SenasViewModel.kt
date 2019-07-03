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
        val senass = MutableLiveData<List<Senas>>()
        var allPalabras : LiveData<List<Senas>>? = null
    }

    fun load(user : String) {

        viewModelScope.launch {
            senass.value = repository.allFavoritos(user)
        }
            //var list =
            //Log.d("com.DevRAT.lesa",list.toString())
            //senass = list

    }


}