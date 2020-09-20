package com.darabi.mohammad.movies.remote.api

import com.darabi.mohammad.movies.remote.api.model.Configuration
import com.darabi.mohammad.movies.remote.api.model.MovieDetail
import com.darabi.mohammad.movies.remote.api.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("configuration")
    suspend fun fetchConfigs(@Query("api_key") apiKey: String): Configuration

    @GET("discover/movie")
    suspend fun fetchMovies(
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") releaseYear: String,
        @Query("page") page: Int
    ): MoviesList

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetail
}