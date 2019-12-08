package com.hing.newsapplication.data

import com.hing.newsapplication.data.gateways.SharedPreferencesGateway
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface UserRepository {
    fun registerUsername(username: String): Completable
    fun updateKeyword(keyword: String): Completable
    fun getKeyword(): Maybe<String>
    fun getUsername(): Maybe<String>
}

class UserRepositoryImpl @Inject constructor(
    private val sharedPreferencesGateway: SharedPreferencesGateway
) : UserRepository {
    override fun registerUsername(username: String): Completable {
        return sharedPreferencesGateway.registerUsername(username)
    }

    override fun updateKeyword(keyword: String): Completable {
        return sharedPreferencesGateway.updateKeyword(keyword)
    }

    override fun getKeyword(): Maybe<String> {
        return sharedPreferencesGateway.getKeyword()
    }

    override fun getUsername(): Maybe<String> {
        return sharedPreferencesGateway.getUsername()
    }
}
