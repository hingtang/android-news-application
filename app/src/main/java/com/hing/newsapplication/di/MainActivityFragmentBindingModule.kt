package com.hing.newsapplication.di

import com.hing.newsapplication.di.scope.FragmentScope
import com.hing.newsapplication.ui.customnews.CustomNewsFragment
import com.hing.newsapplication.ui.customnews.CustomNewsViewModel
import com.hing.newsapplication.ui.profile.ProfileFragment
import com.hing.newsapplication.ui.topheadlinenews.TopHeadlineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by HingTang on 2019-12-07.
 */
@Module
interface MainActivityFragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [TopHeadlineFragmentModule::class])
    fun topHeadlineFragment(): TopHeadlineFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    fun profileFragment(): ProfileFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [CustomNewsFragmentModule::class])
    fun customNewsFragment(): CustomNewsFragment
}
