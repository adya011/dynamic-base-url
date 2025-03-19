package com.nanda.dynamicbaseurl

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(DynamicBaseUrlInterceptor(context))
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createRetrofit(): ApiService =
        retrofit.create(ApiService::class.java)
}