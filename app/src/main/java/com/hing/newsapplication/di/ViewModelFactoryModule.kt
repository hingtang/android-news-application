package com.hing.newsapplication.di

import androidx.lifecycle.ViewModelProvider
import com.hing.newsapplication.viewmodels.NewsViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by HingTang on 2019-12-07.
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun newsViewModelFactory(factory: NewsViewModelFactory): ViewModelProvider.Factory
}
