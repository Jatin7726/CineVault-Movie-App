package com.cinevault.app.domain.usecase

import com.cinevault.app.common.util.Resource
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Movie>>> {
        if (query.isBlank()) return flowOf(Resource.Success(emptyList()))
        return repository.searchMovies(query)
    }
}
