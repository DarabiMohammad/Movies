package com.darabi.mohammad.movies.di.module

import com.darabi.mohammad.movies.repository.Repository
import com.darabi.mohammad.movies.repository.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBuilderModule {

    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository
}