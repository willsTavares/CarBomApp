package com.fiap.ifix.presentation

 import android.content.Intent
 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import android.view.View
 import androidx.fragment.app.Fragment
 import androidx.navigation.findNavController
 import com.fiap.ifix.R
 import com.fiap.ifix.databinding.ActivityMainBinding
 import com.fiap.ifix.databinding.ActivityNavbarBinding

const val BASE_URL = "https://carbom.azurewebsites.net/"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_Ifix)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val button = binding.login
        button.setOnClickListener(){

                val intent = Intent(this, GetLocation::class.java)
                startActivity(intent)

        }
    }


}