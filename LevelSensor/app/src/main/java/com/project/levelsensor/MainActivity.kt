package com.project.levelsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager;
    private lateinit var accelerometer: Sensor;
    var levelingStarted: Boolean = true;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager;
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    fun startLeveling(view: View) {
        levelingStarted = true;
        Toast.makeText(this, levelingStarted.toString(), Toast.LENGTH_SHORT);
    }

    override fun onSensorChanged(event: SensorEvent) {
        val sensorXValue = event.values[0];
        val sensorYValue = event.values[1];
        val sensorTextView = findViewById<TextView>(R.id.sensorResultTextView);
        sensorTextView.text = "X: "+ sensorXValue+ "\nY: "+ sensorYValue;
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onResume(){
        super.onResume();
        if(accelerometer != null && levelingStarted){
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            sensorManager.unregisterListener(this);
        }
    }

    override fun onPause(){
        super.onPause();
        levelingStarted = false;
    }
}