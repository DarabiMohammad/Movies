package com.darabi.mohammad.movies.repository

import com.darabi.mohammad.movies.remote.api.model.discover.Movie

interface Repository {

    suspend fun fetchMovies(apiKey: String, language: String, sortBy: String, releaseYear: String, page: Int): Response<List<Movie>>
}