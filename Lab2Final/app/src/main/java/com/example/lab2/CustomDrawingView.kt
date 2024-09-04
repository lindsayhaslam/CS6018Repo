package com.example.lab2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomDrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
    private val bitmapCanvas = Canvas(bitmap)
    private val paint = Paint()
    private val rect: Rect by lazy { Rect(0, 0, width, height) }

    init {
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, null, rect, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    drawCircleAtTouch(event.x, event.y)
                    return true
                }
            }
        }
        return false
    }

    //Set the color to draw
    //Draw a circle at the touch point
    //Redraw the view using invalidate()
    private fun drawCircleAtTouch(x: Float, y: Float) {
        paint.color = Color.BLACK
        bitmapCanvas.drawCircle(x, y, 10f, paint)
        invalidate()
    }

    fun setBitmap(newBitmap: Bitmap) {
        bitmap = newBitmap
        bitmapCanvas.setBitmap(bitmap)
        invalidate()
    }

    fun getBitmap(): Bitmap {
        return bitmap
    }
}

