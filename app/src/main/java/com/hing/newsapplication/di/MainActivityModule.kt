package com.hing.newsapplication.di

import android.app.Activity
import com.hing.newsapplication.MainActivity
import com.hing.newsapplication.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

/**
 * Created by HingTang on 2019-12-07.
 */
@Module
interface MainActivityModule {
    @Binds
    @ActivityScope
    fun activity(activity: MainActivity): Activity
}
