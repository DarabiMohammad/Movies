package com.darabi.mohammad.movies.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.darabi.mohammad.movies.App
import com.darabi.mohammad.movies.BuildConfig
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.discover.Movie
import com.darabi.mohammad.movies.repository.Repository
import com.darabi.mohammad.movies.repository.Response
import com.darabi.mohammad.movies.repository.Response.Companion.loading
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    val moviesResponse = MutableLiveData<Response<List<Movie>>>()
    fun fetchMovies() = viewModelScope.launch {
        moviesResponse.value = loading()
        moviesResponse.value = repository.fetchMovies(
            BuildConfig.API_KEY, getApplication<App>().getString(R.string.lang),
            "popularity.desc", "2010", 1
        )
    }
}