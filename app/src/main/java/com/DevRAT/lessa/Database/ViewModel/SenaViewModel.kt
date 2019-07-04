package com.DevRAT.lessa.Database.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.DevRAT.lessa.Database.Database.WordDataBase
import com.DevRAT.lessa.Database.Entities.SenaUser
import com.DevRAT.lessa.Database.Repository.SenaUserRepository
import com.DevRAT.lessa.UI.Activities.MainActivity
import kotlinx.coroutines.launch

class SenaViewModel (app: Application) : AndroidViewModel(app) {

    private val senaUserDao = WordDataBase.getDatabase(app,viewModelScope).senaUserDao()
    private val repository = SenaUserRepository(senaUserDao)
    val senass= MutableLiveData<List<SenaUser>>()

    fun load(){
        viewModelScope.launch {
            senass.value  = repository.getFavorito(MainActivity.usery)
        }
    }

    fun insert(senaUser: SenaUser) {
        viewModelScope.launch {
            repository.insert(senaUser)
        }
    }

    fun delete(senaUser: SenaUser){
        viewModelScope.launch {
            repository.delete(senaUser)
        }

    }



}