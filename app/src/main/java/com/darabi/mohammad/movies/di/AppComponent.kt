package com.darabi.mohammad.movies.di

import com.darabi.mohammad.movies.App
import com.darabi.mohammad.movies.di.module.ActivityBuilderModule
import com.darabi.mohammad.movies.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory: AndroidInjector.Factory<App>
}