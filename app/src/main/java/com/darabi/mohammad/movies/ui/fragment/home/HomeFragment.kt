package com.darabi.mohammad.movies.ui.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.remote.api.model.Movie
import com.darabi.mohammad.movies.repository.Response
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
    private val spinnerListener: SpinnerListener
) : BaseFragment(), EndlessAdapterCallback, SpinnerListener.Callback {

    override val layoutRes get() = R.layout.fragment_home

    private val adapter by lazy {
        MoviesRecyclerAdapter(adapterConfigs, this, viewModel.getImagesUrl())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcv_movies.adapter = adapter

        viewModel.moviesResponse.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.LOADING -> onLoading(it.message!!)
                Status.SUCCESS -> onSuccess(it.data!!)
                Status.ERROR -> onError(it.message)
            }
        })
    }

    private fun onLoading(message: String) {
        if(message == Response.DO_REFRESH) {
            adapter.clear()
            prg_loading.visibility = View.VISIBLE
        } else return
    }

    private fun onSuccess(movies: List<Movie>) {
        adapter.setList(movies)
        initViews()
    }

    private fun onError(errorMessage: String?) {
        prg_loading.visibility = View.GONE
        rcv_movies.visibility = View.GONE
        activity?.makeToast(errorMessage ?: getString(R.string.simple_error_mesg))
    }

    private fun initViews() {
        if(spn_release_year.adapter == null) {
            val list = viewModel.getReleaseYears()!!
            spn_release_year.adapter = activity?.let {
                ArrayAdapter(it, R.layout.spn_item_release_year, list)
            }
            spn_release_year.setSelection(list.indexOf(viewModel.year), false)
            spinnerListener.callback = this
            spn_release_year.onItemSelectedListener = spinnerListener
            txt_release_year.visibility = View.VISIBLE
        }
        prg_loading.visibility = View.GONE
    }

    override fun loadNextChunck(page: Int) {
        viewModel.fetchMovies(page)
    }

    override fun onYearClick(year: String) {
        viewModel.fetchMovies(releasYear = year)
    }

    override fun onItemClick(position: Int) {
        viewModel.movieId = position
        navigateTo(containerId = R.id.container_home, fragment = detailFragment, addToBackstack = true)
    }
}