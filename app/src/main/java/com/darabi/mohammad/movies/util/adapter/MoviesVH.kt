package com.darabi.mohammad.movies.util.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.Movie

class MoviesVH : RecyclerView.ViewHolder {

    //Properties
    private lateinit var baseUrl: String
    private lateinit var  requestManager: RequestManager
    private lateinit var  callback: EndlessAdapterCallback

    //Views
    private val poster = itemView.findViewById<ImageView>(R.id.img_poster)
    private val title = itemView.findViewById<TextView>(R.id.txt_title)
    private val releaseYear = itemView.findViewById<TextView>(R.id.txt_released_year)
    private val averageVote = itemView.findViewById<TextView>(R.id.txt_vote_average)

    constructor(
        itemView: View,
        baseUrl: String,
        requestManager: RequestManager,
        callback: EndlessAdapterCallback
    ) : super(itemView) {
        this.baseUrl = baseUrl
        this.requestManager = requestManager
        this.callback = callback
    }

    constructor(itemView: View) : super(itemView)

    fun bindView(movie: Movie) {

        requestManager.asDrawable().placeholder(android.R.color.white).error(R.drawable.ic_outline_error_24)
            .load(baseUrl + movie.posterPath).into(poster)
        title.text = movie.title
        releaseYear.text = movie.releaseDate
        averageVote.text = movie.voteAverage.toString()
        itemView.rootView.setOnClickListener { callback.onItemClick(movie.id) }
    }
}