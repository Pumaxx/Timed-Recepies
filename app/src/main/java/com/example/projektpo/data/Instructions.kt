package com.example.projektpo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="recipies_table")
data class Instructions (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val recerieID: String,
    val stepNumber:Int,
    val stepTime: Int,
    val stepTitle: String

    )