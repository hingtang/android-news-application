package com.hing.newsapplication.di

import android.content.Context
import com.hing.newsapplication.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by HingTang on 2019-12-08.
 */
@Module
class AppModule {
    @Provides
    @Singleton
    @ApplicationContext
    fun applicationContext(app: App): Context {
        return app
    }
}
