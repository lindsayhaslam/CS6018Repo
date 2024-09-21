package com.example.chucknorrisapp

import android.app.Application
import androidx.room.Room

class JokeApplication : Application() {
    lateinit var jokeRepository: JokeRepository
        private set

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(
            this,
            JokeDatabase::class.java,
            "joke_database"
        ).build()
        jokeRepository = JokeRepository(database.jokeDao())
    }
}
