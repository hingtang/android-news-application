package com.hing.newsapplication.di

import androidx.lifecycle.ViewModel
import com.hing.newsapplication.ui.topheadlinenews.TopHeadlineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by HingTang on 2019-12-07.
 */
@Module(includes = [CommonModule::class])
interface TopHeadlineFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(TopHeadlineViewModel::class)
    fun topHeadlineViewModel(topHeadlineViewModel: TopHeadlineViewModel): ViewModel
}
