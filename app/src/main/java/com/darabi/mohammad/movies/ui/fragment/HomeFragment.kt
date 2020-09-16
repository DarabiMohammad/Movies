package com.darabi.mohammad.movies.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.repository.Status
import com.darabi.mohammad.movies.util.adapter.MoviesRecyclerAdapter
import com.darabi.mohammad.movies.util.makeToast
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor(
    private val adapter: MoviesRecyclerAdapter
) : BaseFragment(), View.OnClickListener {

    override val layoutRes = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchMovies()

        viewModel.moviesResponse.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.LOADING -> {} // actions like changing views visibility
                Status.SUCCESS -> { it.data?.let { list ->
                    adapter.setList(list)
                    initViews()
                } }
                Status.ERROR -> activity?.makeToast(it.message ?: getString(R.string.simple_error_mesg))
            }
        })
    }

    private fun initViews() {
        rcv_movies.adapter = adapter
    }

    override fun onClick(view: View?) {
        when(view?.id) {
//            R.id.btn -> viewModel.fetchMovies()
        }
    }
}