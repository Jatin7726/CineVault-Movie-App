package com.cinevault.app.di

import com.cinevault.app.data.repository.AccountRepositoryImpl
import com.cinevault.app.data.repository.MovieRepositoryImpl
import com.cinevault.app.domain.repository.AccountRepository
import com.cinevault.app.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is the one place that "wires" the domain's interface to the
 * data layer's concrete class. Everywhere else in the app only ever
 * depends on MovieRepository (the interface) - this module is the
 * single point you'd touch to swap in a fake for tests.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository
}
