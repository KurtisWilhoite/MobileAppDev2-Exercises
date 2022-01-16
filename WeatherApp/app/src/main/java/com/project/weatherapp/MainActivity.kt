package com.project.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var queue : RequestQueue;
    lateinit var url: String;
    var cityName = "Tampere"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(this);
    }

    fun getWeatherData(view: View){
        cityName = findViewById<EditText>(R.id.searchEditText).text.toString();
        url = "https://api.openweathermap.org/data/2.5/weather?q=${cityName}&units=metric&appid=6c433438776b5be4ac86001dc88de74d"
        val stringRequest = StringRequest(Request.Method.GET, url,
        Response.Listener<String>{ response -> handleResponse(response)},
        Response.ErrorListener { handleVolleyError()});

        queue.add(stringRequest);
    }

    private fun handleResponse(response: String){
        val weatherObject = JSONObject(response);
        val weatherType = weatherObject.getJSONArray("weather").getJSONObject(0).getString("main");
        val temperature = weatherObject.getJSONObject("main").getDouble("temp");
        val windSpeed = weatherObject.getJSONObject("wind").getDouble("speed");


        findViewById<TextView>(R.id.cityTextView).text = cityName;
        findViewById<TextView>(R.id.weatherTextView).text = weatherType;
        findViewById<TextView>(R.id.temperatureTextView).text = temperature.toString() + "C";
        findViewById<TextView>(R.id.windspeedTextView).text = windSpeed.toString() + "m/s";
    }

    private fun handleVolleyError(){
        Toast.makeText(this, "City not found", Toast.LENGTH_LONG).show();
    }

    fun showForecast(view: View) {
        
    }
}