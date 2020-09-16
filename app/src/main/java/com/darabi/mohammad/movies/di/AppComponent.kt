package com.darabi.mohammad.movies.di

import com.darabi.mohammad.movies.App
import com.darabi.mohammad.movies.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelBuilderModule::class,
        RemoteModule::class,
        RepositoryBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory: AndroidInjector.Factory<App>
}