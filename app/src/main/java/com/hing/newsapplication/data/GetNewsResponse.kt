package com.hing.newsapplication.data

/**
 * Created by HingTang on 2019-12-08.
 */
data class GetNewsResponse(
    val status: String? = "",
    val totalResults: Int,
    val articles: List<Article>
)
