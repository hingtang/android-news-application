package com.hing.newsapplication.domains

import com.hing.newsapplication.data.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface RegisterUsernameUseCase {
    fun execute(username: String): Completable
}

class RegisterUsernameUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : RegisterUsernameUseCase {
    override fun execute(username: String): Completable {
        return userRepository.registerUsername(username)
    }
}
