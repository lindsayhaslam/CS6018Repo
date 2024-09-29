package com.example.lab5marble

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

@Composable
fun MarbleScreen(offsetX: Float, offsetY: Float) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val maxWidth = maxWidth
        val maxHeight = maxHeight

        Canvas(modifier = Modifier.fillMaxSize()) {
            //Drawing the marble
            drawRoundRect(
                color = Color.Red,
                topLeft = androidx.compose.ui.geometry.Offset(offsetX, offsetY),
                size = androidx.compose.ui.geometry.Size(50.dp.toPx(), 50.dp.toPx()),
                cornerRadius = CornerRadius(25.dp.toPx(), 25.dp.toPx())
            )
        }
    }
}
