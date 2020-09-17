package com.darabi.mohammad.movies.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.repository.Status
import com.darabi.mohammad.movies.ui.fragment.BaseFragment
import com.darabi.mohammad.movies.ui.fragment.detail.DetailFragment
import com.darabi.mohammad.movies.util.adapter.MoviesRecyclerAdapter
import com.darabi.mohammad.movies.util.adapter.MoviesRecyclerCallback
import com.darabi.mohammad.movies.util.makeToast
import com.darabi.mohammad.movies.util.navigateTo
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor(
    private val adapter: MoviesRecyclerAdapter,
    private val detailFragment: DetailFragment
) : BaseFragment(), MoviesRecyclerCallback {

    override val layoutRes get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.callback = this

        viewModel.fetchMovies()

        viewModel.moviesResponse.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.LOADING -> {} // actions like changing views visibility
                Status.SUCCESS -> { it.data?.let { list ->
                    adapter.setList(list, viewModel.getImagesUrl())
                    initViews()
                } }
                Status.ERROR -> activity?.makeToast(it.message ?: getString(R.string.simple_error_mesg))
            }
        })
    }

    private fun initViews() {
        rcv_movies.adapter = adapter
    }

    override fun onMovieClicked(id: Int) =
        navigateTo(containerId = R.id.container_home, fragment = detailFragment, addToBackstack = true)
}