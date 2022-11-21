package com.fiap.ifix.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.fiap.ifix.databinding.ActivitySignUpBinding
import com.fiap.ifix.repository.UserRepository
import kotlinx.coroutines.launch

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private val repository by lazy {
        UserRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.singUp.setOnClickListener(){
            lifecycleScope.launch {
                launch {
                    createUser()
                }
            }
        }

        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
    }


    private suspend fun createUser(){
        val name = binding.username.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        try {
            repository.createUser(email, name, password)
            Toast.makeText(this, "Cadastro criado com sucesso", Toast.LENGTH_LONG ).show()
            binding.singUp.isVisible = false
            binding.backBtn.isVisible = true
        } catch(e: Exception){
            Toast.makeText(this, "Por favor, tente novamente mais tarde : $e", Toast.LENGTH_LONG ).show()
        }


    }


}