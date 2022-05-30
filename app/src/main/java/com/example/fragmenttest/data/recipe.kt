package com.example.timersinwindows.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipeTable")
data class recipe (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val stepTime: Int,
    val stepTitle: String
)