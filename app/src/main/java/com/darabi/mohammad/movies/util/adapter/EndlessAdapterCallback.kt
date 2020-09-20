package com.darabi.mohammad.movies.util.adapter

interface EndlessAdapterCallback {

    fun loadNextChunck(page: Int)

    fun onItemClick(position: Int)
}