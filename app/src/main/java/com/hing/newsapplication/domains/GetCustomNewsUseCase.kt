package com.hing.newsapplication.domains

import com.hing.newsapplication.data.GetNewsResponse
import com.hing.newsapplication.data.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface GetCustomNewsUseCase {
    fun execute(keyword: String, page: Int, pageSize: Int): Single<GetNewsResponse>
}

class GetCustomNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : GetCustomNewsUseCase {
    override fun execute(keyword: String, page: Int, pageSize: Int): Single<GetNewsResponse> {
        return newsRepository.getCustomNews(keyword, page, pageSize)
    }
}
