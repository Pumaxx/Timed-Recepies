package com.example.timersinwindows.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface recipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipe(dbRecipe: recipe)

    @Query("SELECT * FROM recipeTable ORDER BY id ASC")
    fun readAllData(): LiveData<List<recipe>>
}