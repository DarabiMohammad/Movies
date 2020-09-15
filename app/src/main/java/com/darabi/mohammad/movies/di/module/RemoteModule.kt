package com.darabi.mohammad.movies.di.module

import com.darabi.mohammad.movies.remote.RetrofitBuilder
import com.darabi.mohammad.movies.remote.api.MoviesApi
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

@Module
abstract class RemoteModule {

    companion object {

        @Provides
        fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()

        @Provides
        @JvmStatic
        fun provideMoviesService(loggingInterceptor: HttpLoggingInterceptor): MoviesApi =
            RetrofitBuilder(loggingInterceptor).getInstance()
    }
}