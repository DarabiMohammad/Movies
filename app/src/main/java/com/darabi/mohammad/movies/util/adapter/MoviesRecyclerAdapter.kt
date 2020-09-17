package com.darabi.mohammad.movies.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.discover.Movie
import javax.inject.Inject

class MoviesRecyclerAdapter @Inject constructor(
    private val requestManager: RequestManager
) : RecyclerView.Adapter<MoviesVH>() {

    private lateinit var moviesList: List<Movie>
    private lateinit var imagesUrl: String

    fun setList(moviesList: List<Movie>, imagesUrl: String) {
        this.moviesList = moviesList
        this.imagesUrl = imagesUrl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH =
        MoviesVH(
            LayoutInflater.from(parent.context).inflate(R.layout.rcv_itme_movie, parent, false),
            imagesUrl,
            requestManager
        )

    override fun onBindViewHolder(viewHolder: MoviesVH, position: Int) =
        viewHolder.bindView(moviesList[position])

    override fun getItemCount(): Int = moviesList.size
}