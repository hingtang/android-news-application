package com.hing.newsapplication.domains

import com.hing.newsapplication.data.GetNewsResponse
import com.hing.newsapplication.data.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface GetTopHeadlineNewsUseCase {
    fun execute(page: Int, pageSize: Int): Single<GetNewsResponse>
}

class GetTopHeadlineNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : GetTopHeadlineNewsUseCase {
    override fun execute(page: Int, pageSize: Int): Single<GetNewsResponse> {
        return newsRepository.getTopHeadlineNews(page, pageSize)
    }
}
