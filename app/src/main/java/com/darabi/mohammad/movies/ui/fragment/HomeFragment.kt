package com.darabi.mohammad.movies.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.repository.Status
import com.darabi.mohammad.movies.util.makeToast
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor() : BaseFragment(), View.OnClickListener {

    override val layoutRes = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn.setOnClickListener(this)

        viewModel.moviesResponse.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.LOADING -> {} // actions like changing views visibility
                Status.SUCCESS -> {}
                Status.ERROR -> activity?.makeToast(it.message ?: getString(R.string.simple_error_mesg))
            }
        })
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn -> viewModel.fetchMovies()
        }
    }
}