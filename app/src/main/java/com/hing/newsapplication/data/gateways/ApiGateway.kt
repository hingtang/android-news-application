package com.hing.newsapplication.data.gateways

import com.hing.newsapplication.BuildConfig
import com.hing.newsapplication.data.GetNewsResponse
import com.hing.newsapplication.data.NewsApi
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by HingTang on 2019-12-08.
 */
interface ApiGateway {
    fun getTopHeadlineNews(page: Int, pageSize: Int): Single<GetNewsResponse>
    fun getCustomNews(keyword: String, page: Int, pageSize: Int): Single<GetNewsResponse>
}

class ApiGatewayImpl @Inject constructor(
    private val newsApi: NewsApi
) : ApiGateway {
    override fun getTopHeadlineNews(page: Int, pageSize: Int): Single<GetNewsResponse> {
        return newsApi.getNewsList("us", page = page, pageSize = pageSize, apiKey = BuildConfig.API_KEY)
            .firstOrError()
    }

    override fun getCustomNews(keyword: String, page: Int, pageSize: Int): Single<GetNewsResponse> {
        return newsApi.getNewsList("us", keyword, page, pageSize, BuildConfig.API_KEY)
            .firstOrError()
    }
}
