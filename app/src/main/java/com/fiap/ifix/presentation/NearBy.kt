package com.fiap.ifix.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiap.ifix.R
import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.api.RetrofitInitializer
import com.fiap.ifix.databinding.FragmentNearByBinding
import com.fiap.ifix.model.MechanicItem
import com.fiap.ifix.repository.MechanicRepository
import kotlinx.coroutines.launch

class NearBy : Fragment() {
    private lateinit var binding: FragmentNearByBinding

    private lateinit var adapter: MechanicCardAdapter

    private val repository by lazy {
        MechanicRepository(
            MechanicWebClient()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNearByBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            launch {
                getAll()
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                showMechanics()
            }
        }
    }

    private suspend fun getAll() {
        repository.getAll()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_near_by, container, false)
    }

    private suspend fun showMechanics() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context);
        recyclerView.setHasFixedSize(true)

        val mechanicsResponse =  RetrofitInitializer().mechanicService.getMechanics()
        mechanicsResponse.map { mechancis ->
            Log.i("tag", mechancis.name.toString())
            adapter = MechanicCardAdapter(mechanicsResponse)
            recyclerView.adapter = adapter
        }
          /*  binding.mechanicsCard.visibility =
                if (mechancis.id.isEmpty()) {
                    binding.mechanicsCard.visibility = GONE
                    VISIBLE
                } else {
                    binding.mechanicsCard.visibility = VISIBLE
                    recyclerView.adapter = MechanicCardAdapter(this.context, mechanicsResponse)
                    recyclerView.setHasFixedSize(true)
                    GONE
                }*/


    }

}