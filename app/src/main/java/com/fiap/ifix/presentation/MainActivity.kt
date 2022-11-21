package com.fiap.ifix.presentation

 import android.content.Intent
 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import android.util.Log
 import android.view.View
 import android.widget.Toast
 import androidx.fragment.app.Fragment
 import androidx.lifecycle.lifecycleScope
 import androidx.navigation.findNavController
 import com.fiap.ifix.R
 import com.fiap.ifix.databinding.ActivityMainBinding
 import com.fiap.ifix.databinding.ActivityNavbarBinding
 import com.fiap.ifix.repository.UserRepository
 import kotlinx.coroutines.launch

const val BASE_URL = "https://carbom.azurewebsites.net/"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val repository by lazy {
        UserRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_Ifix)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val button = binding.login
        button.setOnClickListener(){
            lifecycleScope.launch {
                launch {
                    authenticate()
                }
            }
        }

        binding.singUpBtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }


    }

    private suspend fun authenticate(){
        val user = binding.username.text.toString()
        val password = binding.password.text.toString()

        val repo = repository.authenticateUser(user, password)
        if (repo != null) {
            if(repo.isSuccessful){
                Toast.makeText(this, "Bem vindo, $user", Toast.LENGTH_LONG ).show()
                val intent = Intent(this, GetLocation::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Usuario não encontrado", Toast.LENGTH_LONG ).show()
            }
        }


    }

}