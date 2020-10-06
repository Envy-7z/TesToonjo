package com.tif.testoonjo.base
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tif.testoonjo.R
import com.tif.testoonjo.base.BaseView
import com.tif.testoonjo.utils.DialogLoading

abstract class BaseActivity : AppCompatActivity(), BaseView {

    var dialogLoading: DialogLoading? = null

    fun getDialogLoadings() : DialogLoading{
        if(dialogLoading == null){
            dialogLoading = DialogLoading(this, R.style.DialogCustom)
        }
        return dialogLoading!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initView()

    }

    fun showLoading() {
        getDialogLoadings().show()
    }

    fun dismissLoading(){
        if(getDialogLoadings().isShowing){
            getDialogLoadings().dismiss()
        }
    }

    protected abstract fun getLayout(): Int
    protected abstract fun initView()

    abstract override fun showErrorToast()

    abstract override fun showSuccessToast()

}