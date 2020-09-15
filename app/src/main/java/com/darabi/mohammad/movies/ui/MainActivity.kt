package com.darabi.mohammad.movies.ui

import android.os.Bundle
import android.os.Handler
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.ui.fragment.HomeFragment
import com.darabi.mohammad.movies.util.navigateTo
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity @Inject constructor() : DaggerAppCompatActivity() {

    @Inject
    lateinit var homeFragment: HomeFragment

    @Inject
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler.postDelayed(Runnable {
            navigateTo(fragment = homeFragment, isReplace = true)
        }, 2000)
    }
}