package com.project.flashlight

import android.graphics.Color
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private var torchOn = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun flashOnOff(view: View) {
        val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager;
        for(id in cameraManager.getCameraIdList()){
            if(cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true) {
                torchOn = !torchOn;

                val background = findViewById<ConstraintLayout>(R.id.backgroundConstraintLayout);
                if(torchOn){
                    background.setBackgroundColor(Color.YELLOW);
                    cameraManager.setTorchMode(id, true);
                } else {
                    background.setBackgroundColor(Color.BLACK);
                    cameraManager.setTorchMode(id, false);
                }
            }
        }
    }
}