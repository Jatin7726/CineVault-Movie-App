package com.cinevault.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cinevault.app.common.util.Resource
import com.cinevault.app.data.local.MovieDao
import com.cinevault.app.data.local.SessionManager
import com.cinevault.app.data.mapper.toDomain
import com.cinevault.app.data.mapper.toDomainList
import com.cinevault.app.data.mapper.toEntity
import com.cinevault.app.data.paging.PopularMoviesPagingSource
import com.cinevault.app.data.remote.TmdbApi
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.model.MovieDetail
import com.cinevault.app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single source of truth: Room is what the UI actually observes.
 * A network call updates Room, and the UI keeps collecting from Room,
 * so the screen works offline with the last cached data and updates
 * itself automatically once a fresh network response lands.
 */
@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: TmdbApi,
    private val dao: MovieDao,
    private val sessionManager: SessionManager
) : MovieRepository {

    override fun getTrendingMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())

        val cached = dao.observeTrending().first().toDomainList()
        if (cached.isNotEmpty()) emit(Resource.Success(cached))

        try {
            val response = api.getTrendingMovies()
            dao.upsertAll(response.results.map { it.toEntity(isTrending = true) })
            emit(Resource.Success(dao.observeTrending().first().toDomainList()))
        } catch (e: Exception) {
            if (cached.isEmpty()) {
                emit(Resource.Error(e.localizedMessage ?: "Couldn't load trending movies"))
            }
        }
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PopularMoviesPagingSource(api) }
        ).flow

    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.searchMovies(query)
            emit(Resource.Success(response.results.map {
                Movie(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    genreIds = it.genreIds
                )
            }))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Search failed"))
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        try {
            val detail = api.getMovieDetail(movieId)
            val trailerKey = api.getMovieVideos(movieId).results
                .firstOrNull { it.site == "YouTube" && it.type == "Trailer" }
                ?.key
            emit(Resource.Success(detail.toDomain(trailerKey)))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't load movie details"))
        }
    }

    override fun getWatchlist(): Flow<List<Movie>> = flow {
        val accountId = sessionManager.accountId.first()
        if (accountId != null && accountId > 0) {
            try {
                val remoteResponse = api.getAccountWatchlistMovies(accountId)
                val entities = remoteResponse.results.map { dto ->
                    dto.toEntity(isInWatchlist = true)
                }
                dao.upsertAll(entities)
            } catch (_: Exception) {
                // Fall back to local database on network error
            }
        }
        dao.observeWatchlist().map { it.toDomainList() }.collect { list ->
            emit(list)
        }
    }

    override suspend fun toggleWatchlist(movie: Movie) {
        val existing = dao.getById(movie.id)
        if (existing != null) {
            dao.update(existing.copy(isInWatchlist = !existing.isInWatchlist))
        } else {
            dao.upsertAll(listOf(movie.copy(isInWatchlist = true).toEntity()))
        }
    }
}
