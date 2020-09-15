package com.darabi.mohammad.movies.ui.fragment

import android.os.Bundle
import android.view.View
import com.darabi.mohammad.movies.R
import javax.inject.Inject

class HomeFragment @Inject constructor() : BaseFragment() {

    override val layoutRes = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}