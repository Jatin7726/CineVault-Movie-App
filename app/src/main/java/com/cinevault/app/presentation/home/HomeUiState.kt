package com.cinevault.app.presentation.home

import com.cinevault.app.domain.model.Movie

data class HomeUiState(
    val trending: List<Movie> = emptyList(),
    val isLoadingTrending: Boolean = false,
    val errorMessage: String? = null
)
