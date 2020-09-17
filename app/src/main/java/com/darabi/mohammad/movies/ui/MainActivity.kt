package com.darabi.mohammad.movies.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.darabi.mohammad.movies.R
import com.darabi.mohammad.movies.ui.fragment.HomeFragment
import com.darabi.mohammad.movies.util.factory.InjectingFragmentFactory
import com.darabi.mohammad.movies.util.factory.ViewModelFactory
import com.darabi.mohammad.movies.util.navigateTo
import com.darabi.mohammad.movies.vm.MainViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.util.*
import javax.inject.Inject

class MainActivity @Inject constructor() : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var fragmentFactory: InjectingFragmentFactory

    @Inject
    lateinit var homeFragment: HomeFragment

    @Inject
    lateinit var handler: Handler

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = injector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.checkConfigsStatus()

        handler.postDelayed(Runnable {
            if(!supportFragmentManager.isDestroyed)
                navigateTo(fragment = homeFragment, isReplace = true)
        }, 2000)
    }
}