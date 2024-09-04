package com.example.lab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.lab2.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var buttonFunction: () -> Unit = {}

    fun setButtonFunction(newFunc: () -> Unit) {
        buttonFunction = newFunc
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(inflater, container, false)
        val viewModel: DrawingViewModel by activityViewModels()

        binding.startButton.setOnClickListener {
            buttonFunction()
        }

        return binding.root
    }
}
