package com.fiap.ifix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.fiap.ifix.api.ApiRequests
import com.fiap.ifix.api.Mechanic
import com.fiap.ifix.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://carbom.azurewebsites.net/"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_location -> replaceFragment(NearBy())
                R.id.ic_fact_check -> replaceFragment(Services())
                R.id.ic_home -> replaceFragment(Home())
                R.id.ic_user -> replaceFragment(Options())
                else ->{
                }
            }
            true
        }
        getCurrentData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getCurrentData() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO){
            val response = api.getMechanics().awaitResponse()
            if(response.isSuccessful){
                val data = response.body()
                Log.i(TAG, data.toString())
            }
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}