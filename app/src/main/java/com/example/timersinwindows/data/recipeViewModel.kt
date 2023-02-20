package com.example.timersinwindows.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class recipeViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<recipe>>
    private val repository: recipeRepository

    init {
        val recipeDao = recipeDataBase.getDatabase(application).recipeDao()
        repository = recipeRepository(recipeDao)
        readAllData = repository.readAllData
    }

    fun addStep(dbRecipe: recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStep(dbRecipe)
        }
    }
}