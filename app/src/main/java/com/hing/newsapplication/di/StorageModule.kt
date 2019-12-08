package com.hing.newsapplication.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by HingTang on 2019-12-08.
 */
@Module
class StorageModule {
    @Provides
    @Singleton
    fun sharedPreferences(@ApplicationContext applicationContext: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }
}
