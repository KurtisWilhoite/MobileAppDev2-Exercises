package com.project.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ForecastActivity : AppCompatActivity() {
    var url ="https://api.openweathermap.org/data/2.5/forecast?q=Tampere&units=metric&appid=6c433438776b5be4ac86001dc88de74d"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        getWeatherForecast();
    }

    fun getWeatherForecast(){
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String>{ response -> handleResponse(response)},
            Response.ErrorListener { handleVolleyError()});

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private fun handleResponse(response: String){
        val rootJsonObject: JSONObject = JSONObject(response);
        val forecastList = rootJsonObject.getJSONArray("list");
        for(i in 0 until forecastList.length()){
            val forecastListItem: JSONObject = forecastList.getJSONObject(i);
            val temperature: Int = forecastListItem.getJSONObject("main").getDouble("temp").toInt();
            val description: String = forecastListItem.getJSONArray("weather").getJSONObject(0).getString("main").toString();
            val time: String = forecastListItem.getString("dt_txt");

            findViewById<TextView>(R.id.forecastListTextView).append(time +" - "+ description +" - "+ temperature +"C\n");
        }
    }

    private fun handleVolleyError(){
        Toast.makeText(this, "City not found", Toast.LENGTH_LONG).show();
    }
}