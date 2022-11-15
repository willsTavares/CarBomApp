package com.fiap.ifix.presentation

 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import androidx.fragment.app.Fragment
 import com.fiap.ifix.R
 import com.fiap.ifix.databinding.ActivityMainBinding

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
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}