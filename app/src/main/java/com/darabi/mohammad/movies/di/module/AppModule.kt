package com.darabi.mohammad.movies.di.module

import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.darabi.mohammad.movies.App
import com.darabi.mohammad.movies.ui.fragment.home.HomeFragment
import com.darabi.mohammad.movies.util.adapter.MoviesRecyclerCallback
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
    }
}