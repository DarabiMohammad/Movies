package com.darabi.mohammad.movies.ui.fragment.home

import android.os.Bundle
import android.view.View
import com.bumptech.glide.RequestManager
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.Movie
import com.darabi.mohammad.movies.repository.Status
import com.darabi.mohammad.movies.ui.fragment.BaseFragment
import com.darabi.mohammad.movies.ui.fragment.detail.DetailFragment
import com.darabi.mohammad.movies.util.adapter.AdapterConfigs
import com.darabi.mohammad.movies.util.adapter.EndlessAdapterCallback
import com.darabi.mohammad.movies.util.adapter.MoviesRecyclerAdapter
import com.darabi.mohammad.movies.util.makeToast
import com.darabi.mohammad.movies.util.navigateTo
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor(
    private val detailFragment: DetailFragment,
    private val adapterConfigs: AdapterConfigs,
    private val glide: RequestManager
) : BaseFragment(), EndlessAdapterCallback {

    override val layoutRes get() = R.layout.fragment_home

    private val adapter by lazy {
        MoviesRecyclerAdapter(adapterConfigs, glide, this, viewModel.getImagesUrl())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(rcv_movies.adapter == null)
            rcv_movies.adapter = adapter

        viewModel.moviesResponse.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.LOADING -> onLoading()
                Status.SUCCESS -> onSuccess(it.data!!)
                Status.ERROR -> onError(it.message)
            }
        })
    }

    private fun onLoading() {}

    private fun onSuccess(movies: List<Movie>) {
        adapter.setList(movies)
        initViews()
    }

    private fun onError(errorMessage: String?) {
        prg_loading.visibility = View.GONE
        activity?.makeToast(errorMessage ?: getString(R.string.simple_error_mesg))
    }

    private fun initViews() {
        prg_loading.visibility = View.GONE
        layout_movies_host.visibility = View.VISIBLE
    }

    override fun loadNextChunck(page: Int) {
        viewModel.fetchMovies(page)
    }

    override fun onItemClick(position: Int) {
        viewModel.movieId = position
        navigateTo(containerId = R.id.container_home, fragment = detailFragment, addToBackstack = true)
    }
}