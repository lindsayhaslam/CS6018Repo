package com.example.lab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lab2.databinding.FragmentDrawingBinding

class DrawingFragment : Fragment() {

    private val viewModel: DrawingViewModel by activityViewModels()
    private var _binding: FragmentDrawingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawingBinding.inflate(inflater, container, false)

        //Restore the previous drawing if available
        viewModel.getDrawingBitmap()?.let {
            binding.customDrawingView.setBitmap(it)
        }

        //Set up the touch listener
        binding.customDrawingView.setOnTouchListener { v, event ->
            binding.customDrawingView.onTouchEvent(event)
            true
        }

        return binding.root
    }

    //Save the current drawing to the ViewModel
    //Clear the binding reference to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setDrawingBitmap(binding.customDrawingView.getBitmap())
        _binding = null
    }
}
