package com.cinevault.app.domain.usecase

import com.cinevault.app.common.util.Resource
import com.cinevault.app.domain.model.MovieDetail
import com.cinevault.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> = repository.getMovieDetail(movieId)
}
