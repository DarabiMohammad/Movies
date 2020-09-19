package com.darabi.mohammad.movies.ui.fragment.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.MovieDetail
import com.darabi.mohammad.movies.remote.api.model.Name
import com.darabi.mohammad.movies.repository.Status
import com.darabi.mohammad.movies.ui.fragment.BaseFragment
import com.darabi.mohammad.movies.util.extractIntegers
import com.darabi.mohammad.movies.util.makeToast
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment @Inject constructor(
    private val glide: RequestManager,
    private val handler: Handler
) : BaseFragment() {

    override val layoutRes: Int get() = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchMovieDetail()

        viewModel.movieDetailResponse.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.LOADING -> onLoading()
                Status.SUCCESS -> onSuccess(it.data!!)
                Status.ERROR -> onError(it.message)
            }
        })
    }

    private fun onLoading() {
        // Do Appropriate Action
    }

    @SuppressLint("SetTextI18n")
    private fun onSuccess(movieDetail: MovieDetail) {

        glide.asDrawable().load(viewModel.getImagesUrl() + movieDetail.backdropPath)
            .placeholder(android.R.color.white).error(R.drawable.ic_outline_error_24).into(img_poster)

        txt_title.text = movieDetail.title
        txt_overview.text = movieDetail.overview
        txt_released_year.text = "${getString(R.string.released)} : ${movieDetail.releaseDate}"

        checkAndAssign(txt_country, movieDetail.productionCountries)

        txt_runtime.text = "${getString(R.string.runtime)} : ${formatTime(movieDetail.runtime.toString())}"

        checkAndAssign(txt_genres, movieDetail.genres)

        checkAndAssign(txt_languege, movieDetail.spokenLanguages)

        txt_budget.text = "${getString(R.string.budget)} : ${movieDetail.budget.toString()}"

        handler.postDelayed(Runnable {
            prg_loading.visibility = View.GONE
            scroll_view.visibility = View.VISIBLE
        }, 30)
    }

    @SuppressLint("SetTextI18n")
    private fun checkAndAssign(textView: TextView, namesList: List<Name>) {
        if(namesList.isNotEmpty()) {
            var string = ""
            namesList.forEach { name -> string += "${name.name}, "}
            textView.text = "${textView.text} : ${string.substring(0, string.length - 2)}"
        } else textView.visibility = View.GONE
    }

    private fun formatTime(time: String): String {
        val timeInMin = time.extractIntegers().toInt()
        val hour = timeInMin / 60
        val minute = timeInMin % 60
        return "${hour}h ${minute}min"
    }

    private fun onError(message: String?) {
        prg_loading.visibility = View.GONE
        txt_error.visibility = View.VISIBLE
        txt_error.text = message ?: getString(R.string.simple_error_mesg)
    }
}