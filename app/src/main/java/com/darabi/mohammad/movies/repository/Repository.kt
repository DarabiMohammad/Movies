package com.darabi.mohammad.movies.repository

import com.darabi.mohammad.movies.remote.api.model.Configuration
import com.darabi.mohammad.movies.remote.api.model.Movie
import com.darabi.mohammad.movies.remote.api.model.MovieDetail

interface Repository {

    suspend fun fetchImageConfigs(apiKey: String): Response<Configuration>

    suspend fun fetchMovies(apiKey: String, releaseYear: String, page: Int): Response<List<Movie>>

    suspend fun fetchMovieDetail(apiKey: String, movieId: Int): Response<MovieDetail>
}