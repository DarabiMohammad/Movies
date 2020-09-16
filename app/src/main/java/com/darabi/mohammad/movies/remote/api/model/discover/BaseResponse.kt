package com.darabi.mohammad.movies.remote.api.model.discover

interface BaseResponse <out T> {

    val results: T
}