package com.tif.testoonjo.activity.main

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.tif.testoonjo.R
import com.tif.testoonjo.model.MainResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() , MainContract {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_toolbar_title.text = "Contact List"
        toolbar.elevation = 3f
        val position = PreferenceManager.getDefaultSharedPreferences(this).getString("token", "")
        val presenter = MainPresenter(this)
        position?.let { presenter.contactShow(it) }

    }

    override fun onShowLoading() {
        pb_home.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        pb_home.visibility = View.GONE
        rv_home.visibility = View.VISIBLE
    }

    override fun onResponse(results: List<MainResponse.Data>) {
        rv_home.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_home.adapter = MainAdapter(results)

    }

    override fun onFailure(error: Throwable) {
        Log.e(MainActivity::class.java.simpleName, "${error.printStackTrace()}")
    }


}