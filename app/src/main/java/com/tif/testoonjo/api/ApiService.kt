package com.tif.testoonjo.api

import com.tif.testoonjo.model.*
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("/recruitment-api/authenticate")
    fun login(
        @Field("username") userEmail: String,
        @Field("password") userPassword: String
    ): Observable<LoginResponse>

}