package com.tif.testoonjo.activity.main

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.tif.testoonjo.R
import com.tif.testoonjo.model.MainResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Fragment() , MainContract {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_main, container, false)




        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val position = PreferenceManager.getDefaultSharedPreferences(context).getString("token", "")
        val presenter = MainPresenter(this)
        position?.let {
            presenter.contactShow(it)

        }

    }
    override fun onShowLoading() {
        pb_home.visibility = View.VISIBLE
    }


    override fun onHideLoading() {
        pb_home.visibility = View.GONE
        rv_home.visibility = View.VISIBLE

    }

    override fun onResponse(results: List<MainResponse.Data>) {

        rv_home.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rv_home.adapter = MainAdapter(results)


    }

    override fun onFailure(error: Throwable) {
        Log.e(MainActivity::class.java.simpleName, "${error.printStackTrace()}")
    }



}