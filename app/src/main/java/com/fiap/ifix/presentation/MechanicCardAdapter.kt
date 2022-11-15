package com.fiap.ifix.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fiap.ifix.R
import com.fiap.ifix.model.MechanicItem


class MechanicCardAdapter(
    private val context: Context?,
    private val mechanicList: List<MechanicItem>
): RecyclerView.Adapter<MechanicCardAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.mechanic_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int ){
        val mechanic = mechanicList[position]
        holder.add(mechanic)
    }

    override fun getItemCount() = mechanicList.size

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun add(mechanic: MechanicItem) {
            val mechanicName = itemView.findViewById<TextView>(R.id.mechanic_text);
            mechanicName.text = mechanic.name
            val mechanicStars = itemView.findViewById<TextView>(R.id.info_text);
            mechanicStars.text = mechanic.description
        }


    }
}