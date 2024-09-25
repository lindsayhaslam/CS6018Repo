package com.example.chucknorrisapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chucknorrisapp.ui.theme.ChuckNorrisAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm: JokeViewModel by viewModels { JokeViewModelFactory((application as JokeApplication).jokeRepository) }

        setContent {
            ChuckNorrisAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentJoke by vm.currentJoke.observeAsState()
                    Column {

                        Spacer(modifier = Modifier.padding(32.dp))

                        //DISPLAY FOR CURRENT JOKE
                        JokeDisplay(currentJoke)

                        //BUTTON
                        FetchJokeSection(onFetchClick = { vm.fetchJoke() })

                        Spacer(modifier = Modifier.padding(16.dp))

                        Text("Previous Jokes", fontSize = 20.sp)
                        Spacer(modifier = Modifier.padding(16.dp))

                        //DISPLAY FOR PREVIOUS JOKES
                        val allJokes by vm.allJokes.observeAsState()
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            items(allJokes ?: listOf<JokeData>()) { jokeData ->
                                JokeDisplay(jokeData = jokeData)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FetchJokeSection(onFetchClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onFetchClick) {
            Text("Fetch Chuck Norris Joke")
        }
    }
}

@Composable
fun JokeDisplay(jokeData: JokeData?) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        Text(text = jokeData?.joke ?: "No joke yet!")
    }
}

