package com.DevRAT.lessa.Database.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.DevRAT.lessa.Database.Database.WordDataBase
import com.DevRAT.lessa.Database.Entities.SenaUser
import com.DevRAT.lessa.Database.Repository.SenaUserRepository
import kotlinx.coroutines.launch

class SenaViewModel (app: Application) : AndroidViewModel(app) {

    private val senaUserDao = WordDataBase.getDatabase(app,viewModelScope).senaUserDao()
    private val repository = SenaUserRepository(senaUserDao)

    companion object{
        val senass= MutableLiveData<List<SenaUser>>()
        //var allPalabras : LiveData<List<Senas>>? = null
    }

    fun load(){
        //senass.value =
       // var senas : List<SenaUser>? = null

        viewModelScope.launch {
            senass.value  = repository.getFavorito()
        }

        //return senas!!
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