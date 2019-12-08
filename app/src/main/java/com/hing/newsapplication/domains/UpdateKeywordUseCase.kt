package com.hing.newsapplication.domains

import com.hing.newsapplication.data.UserRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface UpdateKeywordUseCase {
    fun execute(keyword: String): Completable
}

class UpdateKeywordUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateKeywordUseCase {
    override fun execute(keyword: String): Completable {
        return userRepository.updateKeyword(keyword)
    }
}
