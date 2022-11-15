package com.fiap.ifix.presentation

 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import androidx.fragment.app.Fragment
 import com.fiap.ifix.R
 import com.fiap.ifix.databinding.ActivityMainBinding

const val BASE_URL = "https://carbom.azurewebsites.net/"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(1000)
        setTheme(R.style.Theme_Ifix)
        setContentView(R.layout.activity_main)
    }
}