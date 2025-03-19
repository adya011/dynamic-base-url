package com.nanda.dynamicbaseurl

import com.nanda.dynamicbaseurl.entity.DataModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/images/search")
    suspend fun getApi(): Response<List<DataModel>>
}