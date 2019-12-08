package com.hing.newsapplication.domains

import com.hing.newsapplication.data.UserRepository
import io.reactivex.Maybe
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface GetUsernameUseCase {
    fun execute(): Maybe<String>
}

class GetUsernameUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUsernameUseCase {
    override fun execute(): Maybe<String> {
        return userRepository.getUsername()
    }
}
