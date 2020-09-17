package com.darabi.mohammad.movies.util.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.darabi.mohammad.movies.BuildConfig
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.discover.Movie

class MoviesVH constructor(itemView: View, private val requestManager: RequestManager): RecyclerView.ViewHolder(itemView) {

    //TODO wrong url
    private val baseUrl = "${BuildConfig.BASE_URL}?api_key=${BuildConfig.API_KEY}"
    private val poster = itemView.findViewById<ImageView>(R.id.img_poster)
    private val title = itemView.findViewById<TextView>(R.id.txt_title)
    private val releaseYear = itemView.findViewById<TextView>(R.id.txt_released_year)
    private val averageVote = itemView.findViewById<TextView>(R.id.txt_vote_average)

    fun bindView(movie: Movie) {

        //TODO find some appropriate pics for place holder and error
        requestManager.asDrawable().placeholder(R.color.colorPrimary).error(R.color.colorAccent)
            .load(baseUrl + movie.posterPath).into(poster)
        title.text = movie.title
        releaseYear.text = movie.releaseDate
        averageVote.text = movie.voteAverage.toString()
    }
}