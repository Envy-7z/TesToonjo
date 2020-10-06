package com.tif.testoonjo.activity.login

import com.tif.testoonjo.base.BaseView
import com.tif.testoonjo.model.LoginResponse


interface LoginContract {

    interface LoginView : BaseView {
        fun checkAlreadyLogin()
        fun onLoginBtnClick()
        fun onLoginResult(pList : LoginResponse)

        fun moveToNextScreen()
    }

    interface LoginPresenter {
        fun doLogin(name: String, passwd: String)
        fun moveToNextActivity()

    }
}