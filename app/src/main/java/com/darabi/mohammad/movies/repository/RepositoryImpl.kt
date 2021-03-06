package com.darabi.mohammad.movies.repository

import com.darabi.mohammad.movies.remote.api.MoviesApi
import com.darabi.mohammad.movies.remote.api.model.BaseResponse
import com.darabi.mohammad.movies.remote.api.model.MovieDetail
import com.darabi.mohammad.movies.repository.Response.Companion.error
import com.darabi.mohammad.movies.repository.Response.Companion.success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: MoviesApi) : Repository {

    private val messageKey = "status_message"

    private suspend inline fun <T> safeApiCall(crossinline apiService: suspend () -> BaseResponse<T>): Response<T> =
            try {
                withContext(Dispatchers.IO) {
                    val response = apiService()
                    success(data = response.results)
                }
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    when(error) {
                        is HttpException ->
                            error(null, error, getErrorMessage(error.response()?.errorBody()))
                        else ->
                            error(null, error, "UnknownException")
                    }
                }
            }

    private fun getErrorMessage(responseBody: ResponseBody?): String =
            try {
                val jsonObject = JSONObject(responseBody!!.string())
                when {
                    jsonObject.has(messageKey) -> jsonObject.getString(messageKey)
                    else -> "Something Wrong Happened"
                }
            } catch (error: Exception) { error.message ?: "Something Wrong Happened" }

    override suspend fun fetchImageConfigs(apiKey: String) =
        safeApiCall { api.fetchConfigs(apiKey) }

    override suspend fun fetchMovies(apiKey: String, releaseYear: String, page: Int) =
        safeApiCall { api.fetchMovies(apiKey, releaseYear, page) }

    override suspend fun fetchMovieDetail(apiKey: String, movieId: Int): Response<MovieDetail> =
        safeApiCall { api.fetchMovieDetail(movieId, apiKey) }
}