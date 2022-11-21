package com.fiap.ifix.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fiap.ifix.R
import com.fiap.ifix.model.Service
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RowServicesAdapter (
    private val context: Context?,
    private val serviceList: List<Service>,
    val serviceSelected: (String?) -> Unit,
): RecyclerView.Adapter<RowServicesAdapter.RowServiceHolder>() {
    private var selectPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowServiceHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_service, parent, false)
        return RowServiceHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RowServiceHolder, @SuppressLint("RecyclerView") position: Int ){
        val mechanic = serviceList[position]
        val service = mechanic.name.toString()
        val price = mechanic.price.toString()
        holder.price.text = context?.getString(R.string.price, service, price)
        holder.radioButton.isChecked = (position == selectPosition)
        holder.radioButton.setOnClickListener {
            serviceSelected(mechanic.id)
            if(position == selectPosition){
                holder.radioButton.isChecked = false
                selectPosition = -1
            }
            else{
                selectPosition = position
                notifyDataSetChanged();
            }
        }
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    class RowServiceHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val price = itemView.findViewById<TextView>(R.id.price);
        val radioButton = itemView.findViewById<RadioButton>(R.id.radioBtn)
    }

}