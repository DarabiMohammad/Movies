package com.darabi.mohammad.movies.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.darabi.mohammad.movies.App
import com.darabi.mohammad.movies.util.adapter.AdapterConfigs
import com.darabi.mohammad.movies.vm.PrefsManager
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
        fun provideHandler() = Handler(Looper.getMainLooper())

        @Provides
        fun provideGlide(application: Application) = Glide.with(application)

        @Provides
        fun provideAdapterConfigs() = AdapterConfigs(isEnabledLoading = false, itemCountPerPage = 10)
    }
}