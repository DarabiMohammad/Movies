package com.darabi.mohammad.movies.remote

import com.darabi.mohammad.movies.BuildConfig
import com.darabi.mohammad.movies.remote.api.MoviesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitBuilder @Inject constructor(private val loggingInterceptor: HttpLoggingInterceptor) {

    private val timeOut = 60L

    fun getInstance(): MoviesApi =
        makeRetrofit(makeOkHttpClient(makeLoggingInterceptor()))

    private fun makeRetrofit(okHttpClient: OkHttpClient): MoviesApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MoviesApi::class.java)

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS).build()

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = loggingInterceptor
        logger.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logger
    }
}