package com.project.mylocationapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity(), LocationListener {
    lateinit var locationManager: LocationManager;
    var latitude: Double = 0.0;
    var longitude: Double = 0.0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager;
    }

    fun startPositioning(view: View) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 0);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this);
    }

    fun openMap(view: View) {
        startPositioning(view);
        var geoLocation = "geo:$latitude,$longitude";
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation.toUri();
        }
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent);
        }
    }

    override fun onLocationChanged(p0: Location) {
        latitude = p0.latitude;
        longitude = p0.longitude;

        findViewById<TextView>(R.id.latitudeTextView).text = "Latitude: "+ String.format("%.5f", p0.latitude);
        findViewById<TextView>(R.id.longitudeTextView).text = "Longitude: "+ String.format("%.5f", p0.longitude);
    }
}