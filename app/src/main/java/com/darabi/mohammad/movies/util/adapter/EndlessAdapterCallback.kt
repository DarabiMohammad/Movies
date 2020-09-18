package com.darabi.mohammad.movies.util.adapter

import java.text.FieldPosition

interface EndlessAdapterCallback {

    fun loadNextChunck(page: Int)

    fun onItemClick(position: Int)
}