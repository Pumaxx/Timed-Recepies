package com.example.projektpo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface recipieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addRecepie(stepTitle:Instructions) //tu było suspend

    @Query("SELECT * FROM recipies_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Instructions>>



    }
