package com.hing.newsapplication.di

import androidx.lifecycle.ViewModel
import com.hing.newsapplication.ui.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by HingTang on 2019-12-07.
 */
@Module
interface ProfileFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun profileViewModel(profileViewModel: ProfileViewModel): ViewModel
}
