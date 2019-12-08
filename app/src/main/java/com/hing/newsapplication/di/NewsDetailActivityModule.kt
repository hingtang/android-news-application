package com.hing.newsapplication.di

import android.app.Activity
import com.hing.newsapplication.di.scope.ActivityScope
import com.hing.newsapplication.ui.newsdetail.NewsDetailActivity
import dagger.Binds
import dagger.Module

/**
 * Created by HingTang on 2019-12-08.
 */
@Module(includes = [CommonModule::class])
interface NewsDetailActivityModule {
    @Binds
    @ActivityScope
    fun activity(newsDetailActivity: NewsDetailActivity): Activity
}
