package com.nanda.dynamicbaseurl

import android.app.Application

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.init(this)
    }
}