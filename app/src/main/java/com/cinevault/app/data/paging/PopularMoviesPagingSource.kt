package com.cinevault.app.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cinevault.app.data.mapper.toDomain
import com.cinevault.app.data.remote.TmdbApi
import com.cinevault.app.data.remote.dto.MovieDto
import com.cinevault.app.domain.model.Movie

/**
 * Paging 3 source for "Popular Movies". Keeps paging simple and
 * network-only; for a full offline-first paged list you'd swap this
 * for a RemoteMediator backed by Room (worth mentioning in an
 * interview as the production-grade next step).
 */
class PopularMoviesPagingSource(
    private val api: TmdbApi
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = api.getPopularMovies(page)
            LoadResult.Page(
                data = response.results.map { it.toMovieDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    private fun MovieDto.toMovieDomain(): Movie = Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genreIds = genreIds
    )
}
