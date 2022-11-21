package com.fiap.ifix.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.fiap.ifix.R
import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.databinding.FragmentNearByBinding
import com.fiap.ifix.model.MechanicItem
import com.fiap.ifix.presentation.adapter.MechanicCardAdapter
import com.fiap.ifix.repository.MechanicRepository
import kotlinx.coroutines.launch

class NearBy : Fragment() {
    private lateinit var binding: FragmentNearByBinding

    private var searchView: String? = ""

    private val repository by lazy {
        MechanicRepository(
            MechanicWebClient()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private suspend fun getAllMech(): List<MechanicItem>? {
        val sharedPreferences = context?.getSharedPreferences("Location", Context.MODE_PRIVATE)
        val latitude = sharedPreferences?.let { Double.fromBits(it.getLong("latitude", 0)) }
        val longitude = sharedPreferences?.let { Double.fromBits(it.getLong("longitude", 0)) }

        return repository.getAll(null, searchView, latitude, longitude, null)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentNearByBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.fragment_near_by, container, false)

        var search = view.findViewById<SearchView>(R.id.searchView)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                lifecycleScope.launch {
                    launch {
                        searchView = p0.toString()
                        getAllMech()
                    }
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        showMechanics(view)
                    }
                }
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                search.clearFocus()
                return false
            }
        })
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
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)


        return try {
            recyclerView.isVisible = true
            recyclerView.adapter = MechanicCardAdapter(this.context, getAllMech()!!){ id ->
                Toast.makeText(this.context, id, Toast.LENGTH_SHORT ).show()
                val intent = Intent(this.context, MechanicDetails::class.java).putExtra("user", id)
                startActivity(intent)
            }
        }
        catch (e: Exception){
            recyclerView.isVisible = false
        }
}
}