package com.cinevault.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity - the on-disk shape of a movie. Kept separate from
 * both the network DTO and the domain model so each layer can change
 * independently (e.g. TMDB renaming a field never touches the UI).
 */
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val isInWatchlist: Boolean = false,
    val isTrending: Boolean = false,
    val isPopular: Boolean = false,
    val cachedAt: Long = System.currentTimeMillis()
)
