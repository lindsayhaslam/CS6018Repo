package com.example.lab1helloworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1helloworld.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val theExtrasBundle = intent.extras!!
        val message = theExtrasBundle.getString("message")
        binding.messageTextView.text = message
    }
}