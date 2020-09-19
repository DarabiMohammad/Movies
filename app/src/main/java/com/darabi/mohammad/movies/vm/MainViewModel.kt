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
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class MainViewModel @Inject constructor(
    application: Application,
    private val prefsManager: PrefsManager,
    private val repository: Repository
) : AndroidViewModel(application) {

    private val apiKey = BuildConfig.API_KEY
    private val currentYear by lazy { Calendar.getInstance().get(Calendar.YEAR) }
    private val defaultPage = 1
    val doRefresh = "do_refresh"

    var movieId = -1
    private var year = currentYear.toString()

    private fun saveConfigs() = viewModelScope.launch {

        val result = repository.fetchImageConfigs(apiKey)
        result.data?.config?.baseUrl?.let { prefsManager.saveBaseImageUrl(it) }
        result.data?.config?.posterSizes?.toSet()?.let { prefsManager.savePosterSizes(it) }
        prefsManager.saveReleaseYears(populateReleaseYears().toSet())
    }

    private fun populateReleaseYears(): ArrayList<String> {
        val years = arrayListOf<String>()
        for(i in 1882 until currentYear + 1) {
            years.add(i.toString())
        }
        return years
    }

    private fun getImageSizesList() = prefsManager.getPosterSizes()?.distinct()

    //hard coding
    private fun getImageSize() = getImageSizesList()?.get(6)

    fun checkConfigsStatus() {
        if(prefsManager.getBaseImageUrl().isEmpty())
            saveConfigs()
    }

    fun getImagesUrl() = "${prefsManager.getBaseImageUrl()}/${getImageSize()}/"

    fun getReleaseYears() = prefsManager.getReleaseYears()?.sorted()?.toTypedArray()

    val moviesResponse = MutableLiveData<Response<List<Movie>>>()
    fun fetchMovies(page: Int = defaultPage, releasYear: String = year) = viewModelScope.launch {
        var loadingState = Response.DO_NOTHING
        if(releasYear != year) {
            loadingState = Response.DO_REFRESH
            year = releasYear
        }
        moviesResponse.value = loading(message = loadingState)
        moviesResponse.value = repository.fetchMovies(
            apiKey, getApplication<App>().getString(R.string.lang), releasYear, page
        )
    }

    val movieDetailResponse = MutableLiveData<Response<MovieDetail>>()
    fun fetchMovieDetail() = viewModelScope.launch {
        movieDetailResponse.value = loading()
        movieDetailResponse.value = repository.fetchMovieDetail(apiKey, movieId)
    }
}