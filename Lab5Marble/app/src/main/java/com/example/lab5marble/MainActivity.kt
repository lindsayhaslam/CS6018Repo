package com.example.lab5marble


import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.lab5marble.ui.theme.Lab5MarbleTheme


class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var gravitySensor: Sensor? = null

    private var _offsetX = mutableStateOf(0f)
    private var _offsetY = mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

        setContent {
             Lab5MarbleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MarbleScreen(offsetX = _offsetX.value, offsetY = _offsetY.value)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        gravitySensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
        } ?: run {
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val x = it.values[0]
            val y = it.values[1]

            _offsetX.value = x * 100
            _offsetY.value = y * 100
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //LEAVE BLANK FOR NOW
    }
}
