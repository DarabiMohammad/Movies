package com.darabi.mohammad.movies.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.Movie
import javax.inject.Inject

class MoviesRecyclerAdapter @Inject constructor(
    configs: AdapterConfigs,
    private val adapterCallback: EndlessAdapterCallback,
    private val imageUrl: String
) : EndlessAdapter<MoviesVH, Movie>(configs, adapterCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH =
        if(viewType == otherViewType)
            MoviesVH(
                LayoutInflater.from(parent.context).inflate(R.layout.rcv_itme_movie, parent, false),
                imageUrl, adapterCallback
            )
        else MoviesVH(LayoutInflater.from(parent.context).inflate(R.layout.rcv_item_movie_loading, parent, false))

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
        super.onBindViewHolder(holder, position)
        if(objects.size > 0)
            holder.bindView(objects[position])
    }
}