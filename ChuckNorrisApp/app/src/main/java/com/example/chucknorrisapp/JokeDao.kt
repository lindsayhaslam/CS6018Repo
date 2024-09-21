package com.example.chucknorrisapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {
    @Insert
    suspend fun insertJoke(joke: JokeData)

    @Query("SELECT * FROM jokes")
    fun getAllJokes(): Flow<List<JokeData>>
}
