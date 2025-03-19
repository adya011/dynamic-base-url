package com.nanda.dynamicbaseurl.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveBaseUrl(value: Int) {
        val editor = sharedPreferences.edit()

        editor.putInt(PREF_KEY, value)
        editor.apply() // or use editor.commit() for synchronous operation
    }

    fun getBaseUrlType(): Int {
        return sharedPreferences.getInt(PREF_KEY, 0)
    }

    fun clearAll() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val PREF_KEY = "sharedpref_base_url"
        private const val PREF_NAME = "MyAppPreferences"
    }
}