package com.nanda.dynamicbaseurl

import com.nanda.dynamicbaseurl.entity.DataModel
import retrofit2.Response

class ApiRepository {
    private val apiService: ApiService = RetrofitClient.createRetrofit()

    suspend fun getApiData(): Response<List<DataModel>> {
        return apiService.getApi()
    }
}