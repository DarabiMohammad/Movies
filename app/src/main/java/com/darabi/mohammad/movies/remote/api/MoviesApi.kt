package com.darabi.mohammad.movies.remote.api

import com.darabi.mohammad.movies.remote.api.model.discover.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("discover/movie")
    suspend fun fetchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("primary_release_year") releaseYear: String,
        @Query("page") page: Int
    ): MoviesList
}