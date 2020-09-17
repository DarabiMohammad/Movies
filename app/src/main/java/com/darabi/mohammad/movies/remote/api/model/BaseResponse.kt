package com.darabi.mohammad.movies.remote.api.model

interface BaseResponse <out T> {

    val results: T
}