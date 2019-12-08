package com.hing.newsapplication.di

import com.hing.newsapplication.MainActivity
import com.hing.newsapplication.di.scope.ActivityScope
import com.hing.newsapplication.ui.newsdetail.NewsDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by HingTang on 2019-12-07.
 */
@Module
interface ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainActivityFragmentBindingModule::class])
    fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [NewsDetailActivityModule::class])
    fun newsDetailActivity(): NewsDetailActivity
}
