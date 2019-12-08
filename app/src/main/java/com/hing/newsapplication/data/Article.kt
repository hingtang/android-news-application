package com.hing.newsapplication.data

import java.io.Serializable

/**
 * Created by HingTang on 2019-12-08.
 */
data class Article(
    val source: Source,
    val author: String?,
    val title: String = "",
    val description: String?,
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    var content: String = ""
) : Serializable
