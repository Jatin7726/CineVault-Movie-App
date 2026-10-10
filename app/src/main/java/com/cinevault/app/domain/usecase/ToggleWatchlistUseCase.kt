package com.cinevault.app.domain.usecase

import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.repository.MovieRepository
import javax.inject.Inject

class ToggleWatchlistUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) = repository.toggleWatchlist(movie)
}
