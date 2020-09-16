package com.darabi.mohammad.movies.util.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.discover.Movie

class MoviesVH constructor(private val itemView: View): RecyclerView.ViewHolder(itemView) {

    private val poster = itemView.findViewById<ImageView>(R.id.img_poster)
    private val title = itemView.findViewById<TextView>(R.id.txt_title)
    private val releaseYear = itemView.findViewById<TextView>(R.id.txt_released_year)
    private val averageVote = itemView.findViewById<TextView>(R.id.txt_vote_average)

    fun bindView(movie: Movie) {

        title.text = movie.title
        releaseYear.text = movie.releaseDate
        averageVote.text = movie.voteAverage.toString()
    }
}