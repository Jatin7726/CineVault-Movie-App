package com.cinevault.app.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinevault.app.common.util.Resource
import com.cinevault.app.domain.model.MovieDetail
import com.cinevault.app.domain.usecase.GetMovieDetailUseCase
import com.cinevault.app.domain.usecase.ToggleWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val detail: MovieDetail? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

/**
 * SavedStateHandle reads the "movieId" nav argument directly -
 * no manual passing of IDs through a constructor, and it survives
 * process death, which an interviewer will often ask about.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetail: GetMovieDetailUseCase,
    private val toggleWatchlist: ToggleWatchlistUseCase
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getMovieDetail(movieId).collect { resource ->
                _uiState.value = when (resource) {
                    is Resource.Loading -> _uiState.value.copy(isLoading = true, errorMessage = null)
                    is Resource.Success -> _uiState.value.copy(detail = resource.data, isLoading = false)
                    is Resource.Error -> _uiState.value.copy(isLoading = false, errorMessage = resource.message)
                }
            }
        }
    }

    fun onAddToWatchlist() {
        val movie = _uiState.value.detail?.movie ?: return
        viewModelScope.launch { toggleWatchlist(movie) }
    }
}
