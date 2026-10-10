package com.cinevault.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cinevault.app.common.util.Resource
import com.cinevault.app.domain.usecase.GetPopularMoviesUseCase
import com.cinevault.app.domain.usecase.GetTrendingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * No Android framework types leak in here beyond ViewModel/StateFlow -
 * the ViewModel only talks to use cases, which is what makes it
 * unit-testable with fakes (see HomeViewModelTest for the pattern).
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingMovies: GetTrendingMoviesUseCase,
    getPopularMovies: GetPopularMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // Paging 3 stream for the "Popular Movies" grid; cachedIn survives
    // configuration changes without re-fetching.
    val popularMovies = getPopularMovies()
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), androidx.paging.PagingData.empty())

    init {
        loadTrending()
    }

    fun loadTrending() {
        viewModelScope.launch {
            getTrendingMovies().collect { resource ->
                _uiState.value = when (resource) {
                    is Resource.Loading -> _uiState.value.copy(isLoadingTrending = true, errorMessage = null)
                    is Resource.Success -> _uiState.value.copy(
                        trending = resource.data,
                        isLoadingTrending = false,
                        errorMessage = null
                    )
                    is Resource.Error -> _uiState.value.copy(
                        isLoadingTrending = false,
                        errorMessage = resource.message
                    )
                }
            }
        }
    }
}
