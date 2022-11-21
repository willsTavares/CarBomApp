package com.fiap.ifix.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private lateinit var adapter: MechanicCardAdapter

    private val repository by lazy {
        MechanicRepository(
            MechanicWebClient()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNearByBinding.inflate(layoutInflater)

    }

    private suspend fun getAll(): List<MechanicItem>? {
        return repository.getAll(null,binding.searchView.query.toString(), null, null, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_near_by, container, false)
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

        recyclerView.adapter = MechanicCardAdapter(this.context, getAll()!!){  id ->
            Toast.makeText(this.context, id, Toast.LENGTH_SHORT ).show()
        }
    }

}