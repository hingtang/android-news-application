package com.hing.newsapplication.di

import com.hing.newsapplication.data.NewsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by HingTang on 2019-12-07.
 */
@Module
class ApiModule {
    @Provides
    fun newsApi(retrofit: Retrofit) = retrofit.create(NewsApi::class.java)
}
