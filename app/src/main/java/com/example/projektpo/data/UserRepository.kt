package com.example.projektpo.data

import androidx.lifecycle.LiveData

class UserRepository(private val recipiedao: recipieDao){

    val readAllData: LiveData<List<Instructions>> = recipiedao.readAllData()
    suspend fun addRecipie(recipie: Instructions){
        recipiedao.addRecepie(recipie)
    }
}