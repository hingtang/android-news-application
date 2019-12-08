package com.hing.newsapplication.di

import com.hing.newsapplication.data.gateways.ApiGateway
import com.hing.newsapplication.data.gateways.ApiGatewayImpl
import com.hing.newsapplication.data.gateways.SharedPreferencesGateway
import com.hing.newsapplication.data.gateways.SharedPreferencesGatewayImpl
import dagger.Binds
import dagger.Module

/**
 * Created by HingTang on 2019-12-07.
 */
@Module(includes = [StorageModule::class, ApiModule::class])
interface GatewayModule {
    @Binds
    fun apiGateway(apiGateway: ApiGatewayImpl): ApiGateway

    @Binds
    fun sharedPreferencesGateway(sharedPreferencesGateway: SharedPreferencesGatewayImpl): SharedPreferencesGateway
}
