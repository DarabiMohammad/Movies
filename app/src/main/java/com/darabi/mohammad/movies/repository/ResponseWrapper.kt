package com.darabi.mohammad.movies.repository

enum class Status { SUCCESS, ERROR, LOADING }

data class Response <out T> (
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null,
    val message: String? = null
) {

    companion object {

        const val DO_REFRESH = "do_refresh"
        const val DO_NOTHING = "do_nothing"

        fun <T> success(data: T): Response<T> =
            Response(status = Status.SUCCESS, data = data)

        fun <T> error(data: T?, throwable: Throwable, message: String): Response<T> =
            Response(status = Status.ERROR, data = data, throwable = throwable, message = message)

        fun <T> loading(data: T? = null, message: String? = DO_NOTHING): Response<T> =
            Response(status = Status.LOADING, data = null, message = message)
    }
}