package com.darabi.mohammad.movies.di.module

import androidx.fragment.app.Fragment
import com.darabi.mohammad.movies.di.FragmentKey
import com.darabi.mohammad.movies.ui.fragment.detail.DetailFragment
import com.darabi.mohammad.movies.ui.fragment.home.HomeFragment
import com.darabi.mohammad.movies.ui.fragment.home.OnSpinnerItemSelectedListener
import com.darabi.mohammad.movies.ui.fragment.home.SpinnerListener
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilderModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindHomeFragment(homeFragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailFragment::class)
    abstract fun bindDetailFragment(detailFragment: DetailFragment): Fragment

    @Binds
    abstract fun bindSpinnerListener(spinnerListener: SpinnerListener): OnSpinnerItemSelectedListener
}