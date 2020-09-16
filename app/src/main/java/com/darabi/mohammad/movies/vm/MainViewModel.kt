package com.darabi.mohammad.movies.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darabi.mohammad.movies.BuildConfig
import com.darabi.mohammad.movies.remote.api.model.discover.Movie
import com.darabi.mohammad.movies.repository.Repository
import com.darabi.mohammad.movies.repository.Response
import com.darabi.mohammad.movies.repository.Response.Companion.loading
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val moviesResponse = MutableLiveData<Response<List<Movie>>>()
    fun fetchMovies() = viewModelScope.launch {
        moviesResponse.value = loading()
        moviesResponse.value = repository.fetchMovies(
            BuildConfig.API_KEY, "en-US", "popularity.desc", "2010", 1
        )
    }
}