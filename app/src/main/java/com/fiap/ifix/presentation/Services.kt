package com.fiap.ifix.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.fiap.ifix.R
import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.model.Order
import com.fiap.ifix.presentation.adapter.ServiceCardAdapter
import com.fiap.ifix.repository.MechanicRepository
import kotlinx.coroutines.launch


class Services : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val repository by lazy {
        MechanicRepository(
            MechanicWebClient()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_services, container, false)
        return view
    }

    private suspend fun getServices(): List<Order>?{
        return repository.getService("1")
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            launch {
                getServices()
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                showServices(view)
            }
        }
    }

    private suspend fun showServices(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCardService)
        recyclerView.adapter = ServiceCardAdapter(getServices()!!)
    }
}