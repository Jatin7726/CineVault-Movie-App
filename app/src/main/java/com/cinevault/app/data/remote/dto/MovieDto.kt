package com.cinevault.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("release_date") val releaseDate: String = "",
    @SerialName("vote_average") val voteAverage: Double = 0.0,
    @SerialName("genre_ids") val genreIds: List<Int> = emptyList()
)

@Serializable
data class MovieResponseDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerialName("total_pages") val totalPages: Int = 1
)

@Serializable
data class GenreDto(val id: Int, val name: String)

@Serializable
data class MovieDetailDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("release_date") val releaseDate: String = "",
    @SerialName("vote_average") val voteAverage: Double = 0.0,
    val runtime: Int = 0,
    val genres: List<GenreDto> = emptyList()
)

@Serializable
data class VideoDto(
    val key: String,
    val site: String,
    val type: String
)

@Serializable
data class VideoResponseDto(val results: List<VideoDto>)
