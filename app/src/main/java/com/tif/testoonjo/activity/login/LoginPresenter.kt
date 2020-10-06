package com.tif.testoonjo.activity.login

import android.content.SharedPreferences
import com.tif.testoonjo.api.ApiManager
import com.tif.testoonjo.api.ResponseCallBack
import com.tif.testoonjo.base.BaseActivity
import com.tif.testoonjo.base.BasePresenter
import com.tif.testoonjo.model.LoginResponse
import com.tif.testoonjo.utils.Utility


class LoginPresenter(var mView: LoginContract.LoginView?) : BasePresenter<LoginContract.LoginView>(),
    LoginContract.LoginPresenter {

    val apiManager = ApiManager((mView as BaseActivity))
    private lateinit var sharedPreferences: SharedPreferences

    override fun start(view: LoginContract.LoginView) {

    }

    override fun doLogin(name: String, passwd: String) {
        if (!Utility.validateForEmptyEditText(name, passwd)) {
            (mView as BaseActivity).showLoading()
            apiManager.doLogin(name, passwd, object : ResponseCallBack<LoginResponse> {
                override fun onSuccess(response: LoginResponse) {
                    (mView as BaseActivity).dismissLoading()
                    try {
                        if (response.token.contains("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9",ignoreCase = true)) {

                            mView?.onLoginResult(response)
                            mView?.showSuccessToast()
                        } else {
                            mView?.showErrorToast()
                        }
                    } catch (ex: Exception) {
                        mView?.showErrorToast()
                    }
                }

                override fun onFailure(e: Throwable) {
                    (mView as BaseActivity).dismissLoading()
                    mView?.showErrorToast()
                }
            })
        } else {
            (mView as BaseActivity).dismissLoading()
            mView?.showErrorToast()
        }
    }

    override fun destroy() {
        mView = null
    }

    override fun moveToNextActivity() {
        //Navigate to appropriate place

    }



}