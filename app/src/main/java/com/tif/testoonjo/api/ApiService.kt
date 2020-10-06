package com.tif.testoonjo.api

import com.tif.testoonjo.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("/recruitment-api/authenticate")
    fun login(
        @Field("username") userEmail: String,
        @Field("password") userPassword: String
    ): Observable<LoginResponse>


    @GET("/recruitment-api/contacts?")
    fun getContact(@Query("token")token: String): Call<MainResponse>

}