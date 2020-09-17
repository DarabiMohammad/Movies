package com.darabi.mohammad.movies.remote.api.model.config

import com.darabi.mohammad.movies.remote.api.model.discover.BaseResponse
import com.google.gson.annotations.SerializedName

data class Configuration (
    @SerializedName("images") val config: ImageConfigs,
    @SerializedName("change_keys") val changeKeys: List<String>
) : BaseResponse<Configuration> {
    override val results: Configuration
        get() = this
}

data class ImageConfigs (
    @SerializedName("base_url") val baseUrl: String,
    @SerializedName("secure_base_url") val secureBaseUrl: String,
    @SerializedName("backdrop_sizes") val backdrop_sizes: List<String>,
    @SerializedName("logo_sizes") val logoSizes: List<String>,
    @SerializedName("poster_sizes") val posterSizes: List<String>,
    @SerializedName("profile_sizes") val profileSizes: List<String>,
    @SerializedName("still_sizes") val stillSizes: List<String>
)