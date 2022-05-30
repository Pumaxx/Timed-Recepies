package com.example.timersinwindows.data

import androidx.lifecycle.LiveData

class recipeRepository (private val recipeDao: recipeDao){

    val readAllData: LiveData<List<recipe>> = recipeDao.readAllData()

    suspend fun addStep(dRecipe: recipe) {
        recipeDao.addRecipe(dRecipe)
    }
}