package com.example.lab2

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DrawingViewModel : ViewModel() {

    private val _drawingBitmap: MutableLiveData<Bitmap> = MutableLiveData()

    val drawingBitmap: LiveData<Bitmap> get() = _drawingBitmap

    fun setDrawingBitmap(bitmap: Bitmap) {
        _drawingBitmap.value = bitmap
    }

    fun getDrawingBitmap(): Bitmap? {
        return _drawingBitmap.value
    }
}

