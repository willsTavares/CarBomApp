package com.fiap.ifix.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fiap.ifix.R
import com.fiap.ifix.model.Order

class ServiceCardAdapter(
    private val serviceList: List<Order>,
): RecyclerView.Adapter<ServiceCardAdapter.ServiceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.service_card, parent, false)
        return ServiceHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int ){
        val mechanic = serviceList[position]
        holder.mechanicName.text = mechanic.mechanic
        holder.serviceName.text = mechanic.name
        holder.createdAt.text = mechanic.formattedDate
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    class ServiceHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mechanicName = itemView.findViewById<TextView>(R.id.title_mechanic_service);
        val createdAt = itemView.findViewById<TextView>(R.id.created_at_service);
        val serviceName = itemView.findViewById<TextView>(R.id.serviceName);

    }
}