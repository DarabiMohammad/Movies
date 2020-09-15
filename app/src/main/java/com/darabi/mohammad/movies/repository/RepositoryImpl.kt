package com.darabi.mohammad.movies.repository

import com.darabi.mohammad.movies.remote.api.MoviesApi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: MoviesApi) : Repository {

}