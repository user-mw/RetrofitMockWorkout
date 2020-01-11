package com.example.retrofitmockworkout.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmockworkout.R
import com.example.retrofitmockworkout.domain.Car

class ItemsAdapter(private val onClickListener: (Int) -> Unit) : RecyclerView.Adapter<ItemsHolder>() {

    var items = listOf<Car>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.car_view, parent, false)

        return ItemsHolder(onClickListener, view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.bind(items[position])
    }
}

class ItemsHolder(private val itemClickListener: (Int) -> Unit, private val view: View) : RecyclerView.ViewHolder(view) {

    private val id = view.findViewById<TextView>(R.id.carId)
    private val text = view.findViewById<TextView>(R.id.carInfo)

    fun bind(item: Car) {
        id.text = item.id.toString()
        text.text = item.toString()

        view.setOnClickListener { itemClickListener(item.id) }
    }
}