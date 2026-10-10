package com.cinevault.app.domain.usecase

import com.cinevault.app.common.util.Resource
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * One use case = one business action. The ViewModel calls
 * `getTrendingMovies()` and never talks to the repository directly -
 * this is the "use case" layer of Clean Architecture, and it's also
 * where you'd add business rules later (e.g. filtering 18+ content)
 * without touching the ViewModel or the repository.
 */
class GetTrendingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = repository.getTrendingMovies()
}
