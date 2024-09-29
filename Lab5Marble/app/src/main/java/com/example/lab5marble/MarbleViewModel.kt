package com.example.lab5marble

import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

class MarbleViewModel(application: Application) : AndroidViewModel(application), SensorEventListener {

    private val sensorManager: SensorManager =
        application.getSystemService(Application.SENSOR_SERVICE) as SensorManager
    private val gravitySensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

    val offsetX = MutableLiveData(0f)
    val offsetY = MutableLiveData(0f)

    init {
        gravitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val gravityX = it.values[0]
            val gravityY = it.values[1]

            offsetX.postValue(gravityX * 100)
            offsetY.postValue(gravityY * 100)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //LEAVE BLANK
    }

    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }
}
