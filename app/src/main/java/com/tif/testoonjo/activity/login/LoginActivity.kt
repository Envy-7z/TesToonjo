package com.tif.testoonjo.activity.login

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.widget.Toast
import com.tif.testoonjo.BuildConfig
import com.tif.testoonjo.activity.main.MainActivity
import com.tif.testoonjo.R
import com.tif.testoonjo.base.BaseActivity
import com.tif.testoonjo.model.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.LoginView {
    private val sharedPrefFile = "login"

    private val mPresenter = LoginPresenter(this)

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        if(intent.hasExtra("401Message")){
            Toast.makeText(applicationContext, intent.getStringExtra("401Message"), Toast.LENGTH_SHORT).show()
        }
        //      checkAlreadyLogin()

        if(BuildConfig.DEBUG){
            etEmail.setText("tonjoo")
            etPass.setText("tonjoo")
        }
        btnLanjut.setOnClickListener {
            onLoginBtnClick()

        }




    }

    override fun checkAlreadyLogin() {
        TODO("Not yet implemented")
    }

    override fun onLoginBtnClick() {
        mPresenter.doLogin(etEmail.text.toString(), etPass.text.toString())

    }

    override fun onLoginResult(pList: LoginResponse) {
        System.out.println("testing ya  ="+pList.token)
        val mContext = applicationContext
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        val sharedPref = mContext.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val edt = preferences.edit()
        edt.putString("token", pList.token)
        edt.apply()
        edt.commit()

        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(Intent(this@LoginActivity, com.tif.testoonjo.MainActivity::class.java))
        finish()
    }



    override fun moveToNextScreen() {
    }



    override fun showErrorToast() {
        Toast.makeText(this, getString(R.string.error_toast), Toast.LENGTH_SHORT).show()
    }

    override fun showSuccessToast() {
        Toast.makeText(this, getString(R.string.success_toast), Toast.LENGTH_SHORT).show()
    }



}