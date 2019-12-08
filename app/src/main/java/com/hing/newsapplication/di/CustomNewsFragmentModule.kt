package com.hing.newsapplication.di

import androidx.lifecycle.ViewModel
import com.hing.newsapplication.ui.customnews.CustomNewsViewModel
import com.hing.newsapplication.ui.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by HingTang on 2019-12-07.
 */
@Module(includes = [CommonModule::class])
interface CustomNewsFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(CustomNewsViewModel::class)
    fun customNewsViewModel(customNewsViewModel: CustomNewsViewModel): ViewModel
}
