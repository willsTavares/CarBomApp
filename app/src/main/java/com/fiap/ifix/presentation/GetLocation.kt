package com.fiap.ifix.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.fiap.ifix.databinding.ActivityGetLocationBinding
import com.google.android.gms.location.*

class GetLocation : AppCompatActivity()  {

    private lateinit var context: Context
    private lateinit var binding: ActivityGetLocationBinding
    lateinit var shared : SharedPreferences
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getLocation()

        binding.getLocationPlease.setOnClickListener {
            enableGPS()
        }

        binding.nextBtn.setOnClickListener() {
            val intent = Intent(this, Navbar::class.java)
            startActivity(intent)
        }
    }

    private fun enableGPS() {
        startActivity(  Intent(ACTION_LOCATION_SOURCE_SETTINGS))
    }

    @SuppressLint("CommitPrefEdits")
    private fun getLocation() {
        var shared = getSharedPreferences("Location" , Context.MODE_PRIVATE)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    if(location != null) {
                        binding.lat.text = location.latitude.toString()
                        binding.lon.text = location.longitude.toString()

                        val addLocation = shared.edit()
                        addLocation.putLong("latitude" , location.latitude.toRawBits())
                        addLocation.putLong("longitude" , location.longitude.toRawBits())
                        addLocation.apply()
                    }
                }
        }

    }


}