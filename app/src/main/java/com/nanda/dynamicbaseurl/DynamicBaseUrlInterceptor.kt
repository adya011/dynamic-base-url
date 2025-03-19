package com.nanda.dynamicbaseurl

import android.content.Context
import com.nanda.dynamicbaseurl.entity.BaseUrlType
import com.nanda.dynamicbaseurl.utils.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Response

class DynamicBaseUrlInterceptor(private val context: Context) : Interceptor {
    private var baseUrl = BuildConfig.BASE_URL.toHttpUrlOrNull()

    private fun checkBaseUrl() {
        val sharedPrefManager = SharedPreferencesManager(context)
        val selectedUrlId = sharedPrefManager.getBaseUrlType()
        val selectedUrl = BaseUrlType.getUrlById(selectedUrlId)

        baseUrl = selectedUrl.toHttpUrlOrNull()
    }

    override fun intercept(chain: Chain): Response {
        var request = chain.request()

        checkBaseUrl()

        baseUrl?.let {
            val newUrl = request.url.newBuilder()
                .scheme(it.scheme)
                .host(it.host)
                .port(it.port)
                .build()

            request = request.newBuilder().url(newUrl).build()
        }

        return chain.proceed(request)
    }
}