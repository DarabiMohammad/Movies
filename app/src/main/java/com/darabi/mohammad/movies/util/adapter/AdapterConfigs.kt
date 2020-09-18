package com.darabi.mohammad.movies.util.adapter

import android.os.Bundle

data class AdapterConfigs (
    val firstPage: Int = 1,
    val itemCountPerPage: Int,
    val isEnabledLoading: Boolean = true
)