package com.darabi.mohammad.movies.vm

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject

class PrefsManager @Inject constructor(private val application: Application) {

    companion object {
        const val PERFS_NAME = "movies_perfs"
        private const val PERFS_IMG_URL = "image_url"
        private const val PERFS_POSTER_SIZES = "poster_sizes"
        private const val PERFS_RELEASE_YEARS = "release_years"
        private const val EMPTY_STRING = ""
    }

    private val prefs: SharedPreferences

    init { prefs = application.getSharedPreferences(PERFS_NAME, Context.MODE_PRIVATE) }

    private val editor by lazy { prefs.edit() }

    fun saveBaseImageUrl(url: String) = editor.putString(PERFS_IMG_URL, url).commit()

    fun getBaseImageUrl(): String = prefs.getString(PERFS_IMG_URL, EMPTY_STRING) ?: EMPTY_STRING

    fun savePosterSizes(posterSizes: Set<String>) =
        editor.putStringSet(PERFS_POSTER_SIZES, posterSizes).commit()

    fun getPosterSizes(): MutableSet<String>? = prefs.getStringSet(PERFS_POSTER_SIZES, null)

    fun saveReleaseYears(years: Set<String>) =
        editor.putStringSet(PERFS_RELEASE_YEARS, years).commit()

    fun getReleaseYears(): MutableSet<String>? = prefs.getStringSet(PERFS_RELEASE_YEARS, null)
}