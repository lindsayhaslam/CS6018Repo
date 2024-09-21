package com.example.chucknorrisapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class JokeData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val joke: String
)
