package com.darabi.mohammad.movies.di.module

import com.darabi.mohammad.movies.util.adapter.MoviesRecyclerAdapter
import dagger.Module
import dagger.Provides

@Module
abstract class FragmentBuilderModule {

    companion object {

        @Provides
        fun provideMoviesRecyclerAdapter() = MoviesRecyclerAdapter()
    }
}