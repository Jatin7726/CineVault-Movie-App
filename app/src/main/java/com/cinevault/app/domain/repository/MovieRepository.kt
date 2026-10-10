package com.cinevault.app.domain.repository

import androidx.paging.PagingData
import com.cinevault.app.common.util.Resource
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

/**
 * The domain layer only ever sees this interface. The implementation
 * (which knows about Retrofit + Room) lives in the data layer and is
 * injected via Hilt. This is what makes the ViewModel unit-testable
 * with a fake repository - no Android framework classes required.
 */
interface MovieRepository {

    fun getTrendingMovies(): Flow<Resource<List<Movie>>>

    fun getPopularMovies(): Flow<PagingData<Movie>>

    fun searchMovies(query: String): Flow<Resource<List<Movie>>>

    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>

    fun getWatchlist(): Flow<List<Movie>>

    suspend fun toggleWatchlist(movie: Movie)
}
