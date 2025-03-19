package com.nanda.dynamicbaseurl.entity

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val url: String?
)