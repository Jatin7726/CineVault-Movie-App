package com.cinevault.app.domain.usecase

import androidx.paging.PagingData
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> = repository.getPopularMovies()
}
