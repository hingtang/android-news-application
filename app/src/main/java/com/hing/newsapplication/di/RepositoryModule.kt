package com.hing.newsapplication.di

import com.hing.newsapplication.data.NewsRepository
import com.hing.newsapplication.data.NewsRepositoryImpl
import com.hing.newsapplication.data.UserRepository
import com.hing.newsapplication.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by HingTang on 2019-12-07.
 */
@Module(includes = [GatewayModule::class])
interface RepositoryModule {
    @Binds
    fun newsRepository(newsRepository: NewsRepositoryImpl): NewsRepository

    @Binds
    @Singleton
    fun userRepository(userRepository: UserRepositoryImpl): UserRepository
}
