package com.tif.testoonjo.activity.login.main

import com.tif.testoonjo.api.ApiService
import com.tif.testoonjo.api.NetworkModule
import com.tif.testoonjo.model.MainResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainContract) {

    fun contactShow(token:String) {
        view.onShowLoading()

        val datasource = NetworkModule.providesHttpAdapter().create(ApiService::class.java)
        datasource.getContact(token).enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                view.onHideLoading()
                view.onResponse(response.body()?.data ?: emptyList())
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                view.onHideLoading()
                view.onFailure(t)
            }
        })
    }

}