package com.tif.testoonjo.api

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tif.testoonjo.BuildConfig
import com.tif.testoonjo.BuildConfig.BASE_URL
import com.tif.testoonjo.utils.RxErrorHandlingCallAdapterFactory
import com.tif.testoonjo.utils.SPHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier

class ApiClient {

    companion object {
        var retrofit: Retrofit? = null
        var retrofitAuth: Retrofit? = null

        private val gson = GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            //         .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .setLenient()
            .serializeNulls()
            .create()

        fun getClient(): Retrofit {
            if (retrofit == null) {
                val gson = Gson()
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                val okHttpClient: OkHttpClient =
                    OkHttpClient().newBuilder().addInterceptor(logInterceptor)
                        .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build()
            }
            return retrofit as Retrofit
        }

        fun getClientAuth(mContext: Context): Retrofit {
            if (retrofitAuth == null) {
                val httpClient = OkHttpClient.Builder()
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(interceptor)
                httpClient.addNetworkInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        //  .header(
                        //     "Authorization",
                        //     SPHelper.getInstance(mContext).getDataString(SPHelper.key_token)
                        //   )
                        .header("Accept", "application/json")
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }

                if (BuildConfig.DEBUG) {
                    Log.e("BERARER", SPHelper.getInstance(mContext).getDataString(SPHelper.key_token))
                }

                val client: OkHttpClient = httpClient.build()

                retrofitAuth = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(mContext))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }

            return retrofitAuth as Retrofit
        }


    }


}