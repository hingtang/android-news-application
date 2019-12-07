package com.hing.newsapplication.navigators

import com.hing.newsapplication.data.Article

/**
 * Created by HingTang on 2019-12-08.
 */
interface NewsItemNavigator {
    fun openNewsDetail(article: Article)
}
