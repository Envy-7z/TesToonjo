package com.tif.testoonjo.api

import android.content.Context
import com.tif.testoonjo.model.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiManager(val context: Context) {

    val apiClient = ApiClient.getClient().create(ApiService::class.java)

    fun doLogin(
        username: String,
        passwd: String,
        responseListener: ResponseCallBack<LoginResponse>
    ) {
        apiClient.login(username, passwd)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: LoginResponse -> responseListener.onSuccess(response) },
                { e: Throwable -> run { responseListener.onFailure(e) } })
    }

}