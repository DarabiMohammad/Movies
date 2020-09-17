package com.darabi.mohammad.movies.repository

import com.darabi.mohammad.movies.remote.api.model.config.Configuration
import com.darabi.mohammad.movies.remote.api.model.discover.Movie

interface Repository {

    suspend fun fetchImageConfigs(apiKey: String): Response<Configuration>

    suspend fun fetchMovies(apiKey: String, language: String, sortBy: String, releaseYear: String, page: Int): Response<List<Movie>>
}