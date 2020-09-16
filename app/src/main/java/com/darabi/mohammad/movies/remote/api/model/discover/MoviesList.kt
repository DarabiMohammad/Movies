package com.darabi.mohammad.movies.remote.api.model.discover

import com.google.gson.annotations.SerializedName

data class MoviesList constructor(
    val page: Int,
    override val results: List<Movie>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
) : BaseResponse<List<Movie>>

data class Movie (
    @SerializedName("poster_path") val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("original_language") val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    val popularity: Float,
    @SerializedName("vote_count") val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Float
)