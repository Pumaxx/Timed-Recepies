package com.example.projektpo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VieWModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Instructions>>
    private val repository: UserRepository

    init{
        val recipiedao = UserDatabase.getDatabase(application).recipieDao()
        repository = UserRepository(recipiedao)
        readAllData = repository.readAllData

    }
    fun addRecipie(recipie: Instructions){
        viewModelScope.launch(Dispatchers.IO){
            repository.addRecipie(recipie)
        }
    }
}