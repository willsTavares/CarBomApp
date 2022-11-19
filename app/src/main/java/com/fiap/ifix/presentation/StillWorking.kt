package com.fiap.ifix.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fiap.ifix.databinding.ActivityStillWorkingBinding

class StillWorking : AppCompatActivity() {

    private  lateinit var binding: ActivityStillWorkingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStillWorkingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.goBack.setOnClickListener{
            onBackPressed()
        }
    }

}