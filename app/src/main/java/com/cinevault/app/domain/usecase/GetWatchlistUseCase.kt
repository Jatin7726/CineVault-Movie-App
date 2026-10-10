package com.cinevault.app.domain.usecase

import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWatchlistUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> = repository.getWatchlist()
}
