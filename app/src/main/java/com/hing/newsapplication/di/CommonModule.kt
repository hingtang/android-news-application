package com.hing.newsapplication.di

import android.content.Context
import com.hing.newsapplication.utils.DateTimeHelper
import com.hing.newsapplication.utils.DateTimeHelperImpl
import com.hing.newsapplication.utils.NetworkHelper
import com.hing.newsapplication.utils.NetworkHelperImpl
import dagger.Module
import dagger.Provides

/**
 * Created by HingTang on 2019-12-07.
 */
@Module
class CommonModule {
    @Provides
    fun networkHelper(@ApplicationContext context: Context): NetworkHelper = NetworkHelperImpl(context)

    @Provides
    fun dateTimeHelper(): DateTimeHelper = DateTimeHelperImpl()
}
