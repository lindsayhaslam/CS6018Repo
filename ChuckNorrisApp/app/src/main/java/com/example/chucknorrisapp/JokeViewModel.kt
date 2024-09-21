package com.example.chucknorrisapp

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {
    val allJokes: LiveData<List<JokeData>> = jokeRepository.getAllJokes().asLiveData()
    private val _currentJoke = MutableLiveData<JokeData>()
    val currentJoke: LiveData<JokeData> = _currentJoke


    fun fetchJoke() {
        viewModelScope.launch {
            val joke = NetworkUtils.fetchRandomJoke()
            jokeRepository.insertJoke(JokeData(joke = joke))
            _currentJoke.value = JokeData(joke = joke)
        }
    }
}

class JokeViewModelFactory(private val repository: JokeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JokeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
