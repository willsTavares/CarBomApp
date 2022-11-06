package com.fiap.ifix

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fiap.ifix.api.ApiRequests
import com.fiap.ifix.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"




class NearBy : Fragment() {
    val BASE_URL = "https://carbom.azurewebsites.net/"
    private var param1: String? = null
    private var param2: String? = null
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
}