package com.example.lab5marble

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

fun getGravData(gravitySensor: Sensor, sensorManager: SensorManager): Flow<FloatArray> {
    return channelFlow {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    channel.trySend(it.values).isSuccess
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        sensorManager.registerListener(listener, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }
}
