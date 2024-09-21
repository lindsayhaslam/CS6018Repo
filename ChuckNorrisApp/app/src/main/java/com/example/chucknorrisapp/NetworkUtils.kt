package com.example.chucknorrisapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object NetworkUtils {
    suspend fun fetchRandomJoke(): String {
        return withContext(Dispatchers.IO) {
            try {
                val connection = URL("https://api.chucknorris.io/jokes/random").openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    connection.inputStream.bufferedReader().use { reader ->
                        val response = reader.readText()
                        val jsonObject = JSONObject(response)
                        jsonObject.getString("value") 
                    }
                } else {
                    throw Exception("Error fetching joke: ${connection.responseCode}")
                }
            } catch (e: Exception) {
                //LOG ERROR
                e.printStackTrace()
                "Failed to fetch joke"
            }
        }
    }
}
