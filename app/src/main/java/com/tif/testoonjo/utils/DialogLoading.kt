package com.tif.testoonjo.utils

import android.app.Dialog
import android.content.Context
import androidx.annotation.StyleRes
import com.tif.testoonjo.R


class DialogLoading : Dialog {

    constructor(context: Context) : super(context) {
        initDialog()
    }

    constructor(context: Context, @StyleRes themeResId: Int) : super(context, themeResId) {
        initDialog()
    }

    private fun initDialog() {
        setContentView(R.layout.progress_dialog)
        setCanceledOnTouchOutside(false)
        setCancelable(true)

//        wv.loadUrl("file:///android_asset/loading.gif")
    }

    fun setTitle(text: String) {
//        txt_title!!.text = text
    }

}
