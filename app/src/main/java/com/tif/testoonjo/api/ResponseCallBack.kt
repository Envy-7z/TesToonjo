package com.tif.testoonjo.api

interface ResponseCallBack<T>
{

    fun onSuccess(response:T)
    fun onFailure(e:Throwable)


}
