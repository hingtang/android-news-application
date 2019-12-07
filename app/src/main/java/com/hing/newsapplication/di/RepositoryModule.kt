package com.hing.newsapplication.di

import com.hing.newsapplication.data.NewsRepository
import com.hing.newsapplication.data.NewsRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * Created by HingTang on 2019-12-07.
 */
@Module(includes = [GatewayModule::class])
interface RepositoryModule {
    @Binds
    fun newsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}
