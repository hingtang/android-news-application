package com.hing.newsapplication.di

import javax.inject.Qualifier

/**
 * Created by HingTang on 2019-12-07.
 */
@Qualifier
annotation class IOScheduler

@Qualifier
annotation class MainScheduler

@Qualifier
annotation class ApplicationContext
