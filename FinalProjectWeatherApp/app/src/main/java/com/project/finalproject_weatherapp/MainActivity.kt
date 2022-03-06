package com.project.finalproject_weatherapp

import android.Manifest
import android.R.string
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.android.gms.location.*
import org.json.JSONObject
import java.util.*
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    lateinit var url: String;
    var cityName = "Tampere"
    lateinit var fusedLocationClient: FusedLocationProviderClient;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWeatherData();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    fun setSearchBar(view:View){
        cityName = findViewById<EditText>(R.id.searchEditText).text.toString();
        getWeatherData();
    }

    fun getWeatherData(){
        url = "https://api.openweathermap.org/data/2.5/weather?q=${cityName}&units=metric&appid=6c433438776b5be4ac86001dc88de74d"
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String>{ response -> handleResponse(response)},
            Response.ErrorListener { handleVolleyError()});

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private fun handleResponse(response: String){
        val weatherObject = JSONObject(response);
        val weatherType = weatherObject.getJSONArray("weather").getJSONObject(0).getString("main");
        val temperature = weatherObject.getJSONObject("main").getDouble("temp");
        val windChill = weatherObject.getJSONObject("main").getDouble("feels_like");
        val windSpeed = weatherObject.getJSONObject("wind").getDouble("speed");
        val windDirection = weatherObject.getJSONObject("wind").getInt("deg");
        val maxTemperature = weatherObject.getJSONObject("main").getDouble("temp_max");
        val minTemperature = weatherObject.getJSONObject("main").getDouble("temp_min");

        val direction = degreesToCompass(windDirection);

        findViewById<TextView>(R.id.locationTextView).text = cityName;
        findViewById<TextView>(R.id.weatherTextView).text = weatherType;
        findViewById<TextView>(R.id.temperatureTextView).text = "${temperature} C (Feels like ${windChill} C)";
        findViewById<TextView>(R.id.windspeedTextView).text = "${windSpeed}m/s (${direction})";
        findViewById<TextView>(R.id.tempspanTextView).text = "${maxTemperature}C to ${minTemperature}C";
    }

    private fun degreesToCompass(windDirection: Int): String {
        val value = Math.floor((windDirection/45)+0.5)
        val directions = arrayOf("↑ N", "↗ NE", "→ E", "↘ SE", "↓ S", "↙ SW", "← W", "↖ NW");
        Toast.makeText(this, "${windDirection}", Toast.LENGTH_LONG).show();
        return directions[(value % 8).toInt()];
    }

    private fun handleVolleyError(){
        Toast.makeText(this, "City not found", Toast.LENGTH_LONG).show();
    }

    fun showForecast(view: View) {
        val intent = Intent(this, ForecastActivity::class.java)
        intent.putExtra("cityName",cityName)
        startActivity(intent)
    }

    fun getLocationWeatherData(view: View) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 0);
            return;
        }

        val mLocationRequest: LocationRequest = LocationRequest.create()
        mLocationRequest.setInterval(60000)
        mLocationRequest.setFastestInterval(5000)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        val mLocationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult == null) {
                    return
                }
            }
        }
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    var latitude =  location.latitude
                    var longitude = location.longitude
                    val geocoder = Geocoder(this, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                    val city = addresses[0].locality;
                    cityName = city;
                    getWeatherData();
                } else {
                    Toast.makeText(this, "Location information not available.", Toast.LENGTH_LONG).show();
                }
            }
    }
}