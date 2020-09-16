package com.darabi.mohammad.movies.di.module

import androidx.fragment.app.FragmentFactory
import com.darabi.mohammad.movies.util.factory.InjectingFragmentFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FragmentFactoryBuilderModule {

    @Binds
    abstract fun bindFragmentFactory(fragmentFactory: InjectingFragmentFactory): FragmentFactory
}