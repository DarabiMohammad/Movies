package com.darabi.mohammad.movies.vm

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PrefsManager @Inject constructor(private val application: Application) {

    private val emptyString = ""

    companion object {
        const val PERFS_NAME = "movies_perfs"
        const val PERFS_IMG_URL = "image_url"
        const val PERFS_POSTER_SIZES = "poster_sizes"
    }

    private val prefs: SharedPreferences by lazy { application.getSharedPreferences(PERFS_NAME, Context.MODE_PRIVATE) }

    fun saveBaseImageUrl(url: String) = prefs.edit().putString(PERFS_IMG_URL, url).apply()

    fun getBaseImageUrl(): String = prefs.getString(PERFS_IMG_URL, emptyString) ?: emptyString

    fun savePosterSizes(posterSizes: Set<String>) =
        prefs.edit().putStringSet(PERFS_POSTER_SIZES, posterSizes).apply()

    fun getPosterSizes(): MutableSet<String>? = prefs.getStringSet(PERFS_POSTER_SIZES, null)
}