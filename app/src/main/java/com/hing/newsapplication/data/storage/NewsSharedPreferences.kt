package com.hing.newsapplication.data.storage

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
class NewsSharedPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun registerUser(username: String) {
        sharedPreferences.edit().putString(USERNAME_KEY, username).apply()
    }

    fun updateKeyword(keyword: String) {
        sharedPreferences.edit().putString(KEYWORD_KEY, keyword).apply()
    }

    fun getKeyword(): String {
        return sharedPreferences.getString(KEYWORD_KEY, DEFAULT_KEYWORD) ?: DEFAULT_KEYWORD
    }

    private companion object {
        const val USERNAME_KEY = "USERNAME_KEY"
        const val KEYWORD_KEY = "KEYWORD_KEY"
        const val DEFAULT_KEYWORD = "Bitcoin"
    }
}
