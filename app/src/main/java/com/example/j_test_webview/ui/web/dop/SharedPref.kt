package com.example.j_test_webview.ui.web.dop

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPref @Inject constructor(private val context: Context?) {

    private val sharedPreferences: SharedPreferences by lazy {
        context!!.getSharedPreferences("SavedConfig", Context.MODE_PRIVATE)
    }
    fun save(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }
    fun read(key: String? = null, value: String? = null): String? {
        return sharedPreferences.getString(key, value)
    }

}