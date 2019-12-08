package com.hing.newsapplication.domains

import com.hing.newsapplication.data.UserRepository
import io.reactivex.Maybe
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface GetKeywordUseCase {
    fun execute(): Maybe<String>
}

class GetKeywordUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetKeywordUseCase {
    override fun execute(): Maybe<String> {
        return userRepository.getKeyword()
    }
}
