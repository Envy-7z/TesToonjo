package com.tif.testoonjo.activity.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tif.testoonjo.R
import com.tif.testoonjo.local.room.ContactDatabase
import com.tif.testoonjo.local.room.entity.ContactEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_main, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getNotesData()

    }



    private fun getNotesData(){
        val database = ContactDatabase.getInstance(requireContext())
        val dao = database.contactDao()
        val listItems = arrayListOf<ContactEntity>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)

    }
    private fun setupRecyclerView(listItems: ArrayList<ContactEntity>){
        rv_home.apply {
            adapter = MainAdapter2(listItems)
            layoutManager = LinearLayoutManager(context)
        }
    }
}