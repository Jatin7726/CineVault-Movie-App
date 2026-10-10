package com.cinevault.app.data.mapper

import com.cinevault.app.data.local.entity.MovieEntity
import com.cinevault.app.data.remote.dto.MovieDetailDto
import com.cinevault.app.data.remote.dto.MovieDto
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.model.MovieDetail

// --- network DTO -> Room entity ---
fun MovieDto.toEntity(
    isTrending: Boolean = false,
    isPopular: Boolean = false,
    isInWatchlist: Boolean = false
): MovieEntity = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    isTrending = isTrending,
    isPopular = isPopular,
    isInWatchlist = isInWatchlist
)

// --- Room entity -> domain model ---
fun MovieEntity.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    isInWatchlist = isInWatchlist
)

fun List<MovieEntity>.toDomainList(): List<Movie> = map { it.toDomain() }

// --- domain model -> Room entity (e.g. toggling watchlist) ---
fun Movie.toEntity(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    isInWatchlist = isInWatchlist
)

fun MovieDetailDto.toDomain(trailerKey: String?): MovieDetail = MovieDetail(
    movie = Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage
    ),
    runtime = runtime,
    genres = genres.map { it.name },
    trailerKey = trailerKey
)
