package com.nanda.dynamicbaseurl.entity

enum class BaseUrlType(val id: Int, val title: String, val url: String) {
    CAT(0,"cat","https://api.thecatapi.com"),
    DOG(1, "dog", "https://api.thedogapi.com");

    companion object {
        fun getTitleById(id: Int): String = entries.find { id == it.id }?.title ?: ""
        fun getUrlById(id: Int): String = entries.find { id == it.id }?.url ?: CAT.url
    }
}