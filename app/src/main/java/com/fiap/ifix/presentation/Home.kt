package com.fiap.ifix.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.fiap.ifix.R
import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.databinding.FragmentHomeBinding
import com.fiap.ifix.model.MechanicItem
import com.fiap.ifix.repository.MechanicRepository
import kotlinx.coroutines.launch


class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    val imageArray = intArrayOf(
        R.drawable.banner2, R.drawable.banner1, R.drawable.banner2 )

    private val repository by lazy {
        MechanicRepository(
            MechanicWebClient()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private suspend fun getAll(): List<MechanicItem>? {
        val sharedPreferences = context?.getSharedPreferences("Location", Context.MODE_PRIVATE)
        val latitude = sharedPreferences?.let { Double.fromBits(it.getLong("latitude", 0)) }
        val longitude = sharedPreferences?.let { Double.fromBits(it.getLong("longitude", 0)) }
        Log.i("Location Repository", latitude.toString())
        Log.i("Location Repository", longitude.toString())
        return repository.getAll(null, null, latitude, longitude, null)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentHomeBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        changeBanner(view)
        return view
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                showMechanics(view)
            }
        }
    }

    private suspend fun showMechanics(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCard)
        recyclerView.adapter = MechanicCardAdapter(this.context, getAll()!!){  id ->
            Toast.makeText(this.context, id, Toast.LENGTH_SHORT ).show()
            val intent = Intent( context, MechanicDetails::class.java).putExtra("user", id)
            startActivity(intent)
        }
    }


    private fun changeBanner(view: View){

        val runnable = object : Runnable {
            var i = 0
            var imageView = view.findViewById<ImageView>(R.id.bannerImg)
            override fun run() {

                imageView.setImageResource(imageArray[i])
                i++
                if (i > imageArray.size - 1) {
                    i = 0
                }
                Handler(Looper.getMainLooper()).postDelayed(this, 10000)
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 5000)
    }
}