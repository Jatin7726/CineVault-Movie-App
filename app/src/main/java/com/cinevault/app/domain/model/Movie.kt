package com.cinevault.app.domain.model

/**
 * Pure domain model - no Retrofit or Room annotations here.
 * That is the point of Clean Architecture: the domain layer
 * doesn't know whether data came from a network or a database.
 */
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val genreIds: List<Int> = emptyList(),
    val isInWatchlist: Boolean = false
) {
    val posterUrl: String?
        get() = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }

    val backdropUrl: String?
        get() = backdropPath?.let { "https://image.tmdb.org/t/p/w780$it" }
}

data class MovieDetail(
    val movie: Movie,
    val runtime: Int,
    val genres: List<String>,
    val trailerKey: String?
)
