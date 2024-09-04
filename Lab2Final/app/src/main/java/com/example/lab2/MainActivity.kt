package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // Setup the initial fragment
        binding.fragmentContainer.getFragment<StartFragment>().setButtonFunction {
            val drawingFragment = DrawingFragment()
            val transaction = this.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, drawingFragment, "drawing_tag")
            transaction.addToBackStack(null)
            transaction.commit()
        }

        setContentView(binding.root)
    }
}
