package com.tif.testoonjo.activity.login.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tif.testoonjo.R
import com.tif.testoonjo.model.MainResponse
import kotlinx.android.synthetic.main.list_main.view.*

class MainAdapter(private val results: List<MainResponse.Data>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.list_main,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(results[holder.adapterPosition])
    }


    override fun getItemCount(): Int {
        return results.count()
    }

    fun refreshAdapter(result: MainResponse.Data) {
        this.results.contains(result)
        notifyItemRangeChanged(0, this.results.size)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: MainResponse.Data) {
            with(itemView) {


                tvFirst.text = result.first_name + " -"
                tvLast.text = result.last_name
                tvGender.text = result.gender
                tvEmail.text = result.email
                val foto = findViewById<ImageView>(R.id.ivGambar)
                Glide.with(this)
                    .load(result.avatar)
                    .into(foto)


            }
        }
    }
}