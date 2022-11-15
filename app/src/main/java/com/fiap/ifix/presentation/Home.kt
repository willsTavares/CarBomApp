package com.fiap.ifix.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fiap.ifix.R
import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.api.RetrofitInitializer
import com.fiap.ifix.databinding.FragmentHomeBinding
import com.fiap.ifix.databinding.FragmentNearByBinding
import com.fiap.ifix.model.MechanicItem
import com.fiap.ifix.repository.MechanicRepository
import kotlinx.coroutines.launch


class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    private val repository by lazy {
        MechanicRepository(
            MechanicWebClient()
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private suspend fun getAll(): List<MechanicItem>? {
        return repository.getAll(null,binding.searchHome.query.toString(), null, null, null)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentHomeBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

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
        recyclerView.adapter = MechanicCardAdapter(this.context, getAll()!!)
    }
}