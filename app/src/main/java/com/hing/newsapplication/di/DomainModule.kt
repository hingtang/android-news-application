package com.hing.newsapplication.di

import com.hing.newsapplication.domains.GetTopHeadlineNewsUseCase
import com.hing.newsapplication.domains.GetTopHeadlineNewsUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by HingTang on 2019-12-07.
 */
@Module(includes = [RepositoryModule::class])
interface DomainModule {
    @Binds
    @Singleton
    fun getTopHeadlineNewsUseCase(getTopHeadlineNewsUseCase: GetTopHeadlineNewsUseCaseImpl): GetTopHeadlineNewsUseCase
}
