package com.example.lab1helloworld

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.lab1helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            val messageBundle = Bundle()
            messageBundle.putString("message", binding.button1.text.toString())
            intent.putExtras(messageBundle)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            val messageBundle = Bundle()
            messageBundle.putString("message", binding.button2.text.toString())
            intent.putExtras(messageBundle)
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            val messageBundle = Bundle()
            messageBundle.putString("message", binding.button3.text.toString())
            intent.putExtras(messageBundle)
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            Toast.makeText(this, "Button 4 clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            val messageBundle = Bundle()
            messageBundle.putString("message", binding.button4.text.toString())
            intent.putExtras(messageBundle)
            startActivity(intent)
        }

        binding.button5.setOnClickListener {
            Toast.makeText(this, "Button 5 clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SecondActivity::class.java)
            val messageBundle = Bundle()
            messageBundle.putString("message", binding.button5.text.toString())
            intent.putExtras(messageBundle)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
