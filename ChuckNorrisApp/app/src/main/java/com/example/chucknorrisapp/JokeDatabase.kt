package com.example.chucknorrisapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JokeData::class], version = 1, exportSchema = false)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}
