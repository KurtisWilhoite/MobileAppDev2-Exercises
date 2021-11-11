package com.project.exercise1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculateBmi(view: View) {
        val weightEditText = findViewById<EditText>(R.id.weightEditText);
        val heightEditText = findViewById<EditText>(R.id.heightEditText);

        val weight = weightEditText.text.toString().toDouble();
        val height = heightEditText.text.toString().toDouble();
        val bmi = weight / (height / 100 * height / 100);

        findViewById<TextView>(R.id.bmiResult).text = bmi.toString();
    }
}