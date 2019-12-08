package com.hing.newsapplication.di

import com.hing.newsapplication.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by HingTang on 2019-12-07.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        ViewModelFactoryModule::class,
        DomainModule::class,
        AppModule::class,
        GlobalModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<App>
}
