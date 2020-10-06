package com.tif.testoonjo.activity.login.main

import com.tif.testoonjo.model.MainResponse

interface MainContract {

    fun onShowLoading()
    fun onHideLoading()
    fun onResponse(results: List<MainResponse.Data>)

    fun onFailure(error: Throwable)

}