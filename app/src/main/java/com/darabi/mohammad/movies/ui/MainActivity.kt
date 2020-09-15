package com.darabi.mohammad.movies.ui

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.ui.fragment.HomeFragment
import com.darabi.mohammad.movies.util.factory.ViewModelFactory
import com.darabi.mohammad.movies.util.navigateTo
import com.darabi.mohammad.movies.vm.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity @Inject constructor() : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var homeFragment: HomeFragment

    @Inject
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        handler.postDelayed(Runnable {
            navigateTo(fragment = homeFragment, isReplace = true)
        }, 2000)
    }
}