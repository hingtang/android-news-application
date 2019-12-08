package com.hing.newsapplication.data.gateways

import android.content.SharedPreferences
import com.hing.newsapplication.data.gateways.SharedPreferencesGateway.Companion.KEYWORD
import com.hing.newsapplication.data.gateways.SharedPreferencesGateway.Companion.USERNAME
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface SharedPreferencesGateway {
    fun registerUsername(username: String): Completable
    fun updateKeyword(keyword: String): Completable
    fun getKeyword(): Maybe<String>
    fun getUsername(): Maybe<String>

    companion object {
        internal const val USERNAME = "USERNAME"
        internal const val KEYWORD = "KEYWORD"
    }
}

class SharedPreferencesGatewayImpl @Inject constructor(
    private val preferences: SharedPreferences
) : SharedPreferencesGateway {

    override fun registerUsername(username: String): Completable {
        return Completable.fromCallable {
            preferences.edit().putString(USERNAME, username).apply()
        }
    }

    override fun updateKeyword(keyword: String): Completable {
        return Completable.fromCallable {
            preferences.edit().putString(KEYWORD, keyword).apply()
        }
    }

    override fun getKeyword(): Maybe<String> {
        return Maybe.just(Unit)
            .flatMap {
                val keyword = preferences.getString(KEYWORD, "")
                if (keyword.isNullOrEmpty()) {
                    Maybe.empty()
                } else {
                    Maybe.just(keyword)
                }
            }
    }

    override fun getUsername(): Maybe<String> {
        return Maybe.just(Unit)
            .flatMap {
                val keyword = preferences.getString(USERNAME, "")
                if (keyword.isNullOrEmpty()) {
                    Maybe.empty()
                } else {
                    Maybe.just(keyword)
                }
            }
    }
}
