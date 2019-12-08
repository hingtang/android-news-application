package com.hing.newsapplication.di

import com.hing.newsapplication.domains.*
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

    @Binds
    @Singleton
    fun registerUsernameUseCase(registerUsernameUseCase: RegisterUsernameUseCaseImpl): RegisterUsernameUseCase

    @Binds
    @Singleton
    fun updateKeywordUseCase(updateKeywordUseCase: UpdateKeywordUseCaseImpl): UpdateKeywordUseCase

    @Binds
    @Singleton
    fun getKeywordUseCase(getKeywordUseCase: GetKeywordUseCaseImpl): GetKeywordUseCase

    @Binds
    @Singleton
    fun getUsernameUseCase(getUsernameUseCase: GetUsernameUseCaseImpl): GetUsernameUseCase
}
