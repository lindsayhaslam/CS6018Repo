package com.example.lab5marble

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity
import com.example.lab5marble.ui.theme.Lab5MarbleTheme
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
        val gravityFlow: Flow<FloatArray>? = gravitySensor?.let { getGravData(it, sensorManager) }

        setContent {
            Lab5MarbleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    drawScreen(gravityFlow)
                }
            }
        }
    }
}

@Composable
fun drawScreen(gravityFlow: Flow<FloatArray>?) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val maxWidthPx = constraints.maxWidth.toFloat()
        val maxHeightPx = constraints.maxHeight.toFloat()
        val marbleSize = 50.dp
        val currentDensity = LocalDensity.current
        val marbleSizePx = with(currentDensity) { marbleSize.toPx() }

        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        gravityFlow?.let {
            LaunchedEffect(key1 = it) {
                it.collect { gravityReading ->
                    //Updates the offset depending on the readings
                    //The -gravityReading to adjust the title
                    offsetX += -gravityReading[0] * 10
                    offsetY += gravityReading[1] * 10

                    //To stay within the boundaries of the screen
                    offsetX = offsetX.coerceIn(0f, maxWidthPx - marbleSizePx)
                    offsetY = offsetY.coerceIn(0f, maxHeightPx - marbleSizePx)
                }
            }
        }

        val offsetXDp: Dp = with(currentDensity) { offsetX.toDp() }
        val offsetYDp: Dp = with(currentDensity) { offsetY.toDp() }

        Box(
            modifier = Modifier
                .offset(x = offsetXDp, y = offsetYDp)
                .background(color = Color.Red, shape = CircleShape)
                .height(marbleSize)
                .width(marbleSize)
        )
    }
}
