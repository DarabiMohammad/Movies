package com.darabi.mohammad.movies.di.module

import android.app.Application
import android.os.Bundle
import android.os.Handler
import com.darabi.mohammad.movies.App
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: App): Application

    companion object {

        @Provides
        fun provideBundle() = Bundle()

        @Provides
        fun provideHandler() = Handler()
    }
}