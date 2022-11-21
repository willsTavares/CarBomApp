package com.fiap.ifix.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fiap.ifix.R
import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.databinding.ActivityMechanicDetailsBinding
import com.fiap.ifix.model.MechanicItem
import com.fiap.ifix.model.Service
import com.fiap.ifix.presentation.adapter.RowServicesAdapter
import com.fiap.ifix.repository.MechanicRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class MechanicDetails : AppCompatActivity() {

    private lateinit var binding: ActivityMechanicDetailsBinding

    private var selectedId = ""
    private var selectedServiceId = ""

    private val repository by lazy {
        MechanicRepository(
            MechanicWebClient()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ifix)

        super.onCreate(savedInstanceState)
        binding = ActivityMechanicDetailsBinding.inflate(layoutInflater)
        binding.floatingActionButton.isEnabled = false
        setContentView(binding.root)
        lifecycleScope.launch{
            launch{
               getAll()
        }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                initDate()
            }
        }
        binding.returnIcon.setOnClickListener(){
            onBackPressed()
        }

        binding.floatingActionButton.setOnClickListener(){
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    postService()
                }
            }
        }

    }

    private suspend fun getAll(): List<MechanicItem>? {
        var intent = intent.extras
        var id = intent?.getString("user")
        return repository.getAll(id, null, null, null, null)
    }

    private suspend fun initDate(){
        getAll()?.map { mechDetails ->
            selectedId = mechDetails.id
            binding.mechanicTitle.text = mechDetails.name
            binding.mechanicDescription.text = mechDetails.description
            Picasso.get().load(mechDetails.image).into(binding.cardImg)
            showServices(mechDetails.services)
        }
    }

    private fun showServices(services: List<Service>?) {
        val recyclerView = binding.recyclerViewRow
        recyclerView.adapter = RowServicesAdapter(this, services!!){ id ->
            selectedServiceId = id.toString()
            binding.floatingActionButton.isEnabled = true

        }
    }

    private suspend fun postService(){
        repository.postService(selectedId, selectedServiceId, "1")
    }
}
