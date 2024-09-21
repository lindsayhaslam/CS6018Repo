package com.example.chucknorrisapp

import kotlinx.coroutines.flow.Flow

class JokeRepository(private val jokeDao: JokeDao) {
    fun getAllJokes(): Flow<List<JokeData>> {
        return jokeDao.getAllJokes()
    }

    suspend fun insertJoke(joke: JokeData) {
        jokeDao.insertJoke(joke)
    }
}
