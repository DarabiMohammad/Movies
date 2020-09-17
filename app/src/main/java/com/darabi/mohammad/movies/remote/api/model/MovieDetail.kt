package com.darabi.mohammad.movies.remote.api.model

import com.google.gson.annotations.SerializedName

data class MovieDetail (
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("belongs_to_collection") val belongsToCollection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String?,
    val popularity: Float,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<Company>,
    @SerializedName("production_countries") val productionCountries: List<Country>,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLang>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("vote_count") val voteCount: Int
) : BaseResponse<MovieDetail> {
    override val results: MovieDetail
        get() = this
}

open class Name { val name = "" }

data class Genre ( val id: Int ) : Name()

data class Company (
    val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("origin_country") val originCountry: String
) : Name()

data class Country ( @SerializedName("iso_3166_1") val isoName: String ) : Name()

data class SpokenLang ( @SerializedName("iso_639_1") val isoName: String ) : Name()