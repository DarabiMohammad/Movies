package com.darabi.mohammad.movies.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.discover.Movie
import javax.inject.Inject

class MoviesRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<MoviesVH>() {

    private lateinit var moviesList: List<Movie>

    fun setList(moviesList: List<Movie>) {
        this.moviesList = moviesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH =
        MoviesVH(LayoutInflater.from(parent.context).inflate(R.layout.rcv_itme_movie, parent, false))

    override fun onBindViewHolder(viewHolder: MoviesVH, position: Int) =
        viewHolder.bindView(moviesList[position])

    override fun getItemCount(): Int = moviesList.size
}