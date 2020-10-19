package com.tif.testoonjo.activity.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tif.testoonjo.R
import com.tif.testoonjo.local.room.entity.ContactEntity

class MainAdapter2(
    private val listItems: ArrayList<ContactEntity>
) : RecyclerView.Adapter<MainAdapter2.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_main, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = listItems[position]
        holder.tvFirst.text = item.first_name
        holder.tvLast.text = item.last_name
        holder.tvEmail.text = item.email
        holder.tvGender.text = item.gender

    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFirst = itemView.findViewById<TextView>(R.id.tvFirst)
        var tvLast = itemView.findViewById<TextView>(R.id.tvLast)
        var tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
        var tvGender = itemView.findViewById<TextView>(R.id.tvGender)

    }


}