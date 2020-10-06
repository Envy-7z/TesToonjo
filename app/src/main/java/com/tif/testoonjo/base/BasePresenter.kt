package com.tif.testoonjo.base
import com.tif.testoonjo.navigator.Navigator

abstract class BasePresenter<V : BaseView>{
    val navigator = Navigator.getNavigator()
    abstract fun start(view:V)
    abstract fun destroy()

}