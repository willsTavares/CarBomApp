package com.fiap.ifix.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import com.fiap.ifix.databinding.ActivityMainBinding
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