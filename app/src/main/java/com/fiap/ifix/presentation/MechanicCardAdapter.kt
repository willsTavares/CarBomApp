package com.fiap.ifix.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fiap.ifix.R
import com.fiap.ifix.model.MechanicItem
import kotlin.text.*


class MechanicCardAdapter(
    private val context: Context?,
    private val mechanicList: List<MechanicItem>
): RecyclerView.Adapter<MechanicCardAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.mechanic_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int ){
        val mechanic = mechanicList[position]
        val ranking = String.format("%,.1f", mechanic.ranking)
        val distance = String.format("%,.1f", mechanic.distance)
        holder.mechanicName.text = mechanic.name
        holder.mechanicStars.text = context?.getString(R.string.default_mechanic_near, ranking, distance)
        holder.mechanicsFirstTag.text = mechanic.services?.first()?.name.toString()
        holder.mechanicSecondTag.text = mechanic.services?.last()?.name.toString()
    }

    override fun getItemCount(): Int {
        return mechanicList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mechanicName = itemView.findViewById<TextView>(R.id.mechanic_text);
        val mechanicStars = itemView.findViewById<TextView>(R.id.info_text);
        val mechanicsFirstTag = itemView.findViewById<TextView>(R.id.first_chip);
        val mechanicSecondTag = itemView.findViewById<TextView>(R.id.second_chip);
    }
}