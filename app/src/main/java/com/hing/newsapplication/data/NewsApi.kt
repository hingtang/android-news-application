package com.hing.newsapplication.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by HingTang on 2019-12-07.
 */
interface NewsApi {
    @Headers(
        "Accept: application/json;charset=utf-t",
        "Accept-Language: en"
    )
    @GET("v2/top-headlines")
    fun getTopHeadlineNews(
        @Query("country") country: String,
        @Query("q") keyword: String? = "",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Observable<GetNewsResponse>
}
