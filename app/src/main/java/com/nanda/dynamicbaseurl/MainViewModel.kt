package com.nanda.dynamicbaseurl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = ApiRepository()

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _displayText = MutableLiveData<String>()
    val displayText: LiveData<String> = _displayText


    fun fetchData() {
        viewModelScope.launch {
            try {
                val response = repository.getApiData()
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    _imageUrl.value = response.body()!![0].url
                } else {
                    _error.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }
}