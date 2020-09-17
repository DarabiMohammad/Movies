package com.darabi.mohammad.movies.di.module

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.darabi.mohammad.movies.di.FragmentKey
import com.darabi.mohammad.movies.ui.fragment.HomeFragment
import com.darabi.mohammad.movies.util.adapter.MoviesRecyclerAdapter
import com.darabi.mohammad.movies.util.factory.InjectingFragmentFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilderModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindHomeFragment(homeFragment: HomeFragment): Fragment

    companion object {

        @Provides
        fun provideGlide(application: Application) = Glide.with(application)

        @Provides
        fun provideMoviesRecyclerAdapter(requestManager: RequestManager) =
            MoviesRecyclerAdapter(requestManager)
    }
}