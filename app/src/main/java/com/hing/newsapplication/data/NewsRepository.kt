package com.hing.newsapplication.data

import com.hing.newsapplication.data.gateways.ApiGateway
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface NewsRepository {
    fun getTopHeadlineNews(page: Int, pageSize: Int): Single<GetNewsResponse>
}

class NewsRepositoryImpl @Inject constructor(
    private val apiGateway: ApiGateway
) : NewsRepository {
    override fun getTopHeadlineNews(page: Int, pageSize: Int): Single<GetNewsResponse> {
        return apiGateway.getTopHeadlineNews(page, pageSize)
    }
}
