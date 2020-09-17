package com.darabi.mohammad.movies.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.darabi.mohammad.movies.App
import com.darabi.mohammad.movies.BuildConfig
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.Movie
import com.darabi.mohammad.movies.remote.api.model.MovieDetail
import com.darabi.mohammad.movies.repository.Repository
import com.darabi.mohammad.movies.repository.Response
import com.darabi.mohammad.movies.repository.Response.Companion.loading
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    private val prefsManager: PrefsManager,
    private val repository: Repository
) : AndroidViewModel(application) {

    private val apiKey = BuildConfig.API_KEY

    var movieId = -1

    private fun saveConfigs() = viewModelScope.launch {

        val result = repository.fetchImageConfigs(apiKey)
        result.data?.config?.baseUrl?.let { prefsManager.saveBaseImageUrl(it) }
        result.data?.config?.posterSizes?.toSet()?.let { prefsManager.savePosterSizes(it) }
    }

    private fun getImageSizesList() = prefsManager.getPosterSizes()?.distinct()

    //hard coding
    private fun getImageSize() = getImageSizesList()?.get(6)

    fun checkConfigsStatus() {
        if(prefsManager.getBaseImageUrl().isEmpty())
            saveConfigs()
    }

    fun getImagesUrl() = "${prefsManager.getBaseImageUrl()}/${getImageSize()}/"

    val moviesResponse = MutableLiveData<Response<List<Movie>>>()
    fun fetchMovies() = viewModelScope.launch {
        moviesResponse.value = loading()
        moviesResponse.value = repository.fetchMovies(
            apiKey, getApplication<App>().getString(R.string.lang),
            "popularity.desc", "2010", 1
        )
    }

    val movieDetailResponse = MutableLiveData<Response<MovieDetail>>()
    fun fetchMovieDetail() = viewModelScope.launch {
        movieDetailResponse.value = loading()
        movieDetailResponse.value = repository.fetchMovieDetail(apiKey, movieId)
    }
}