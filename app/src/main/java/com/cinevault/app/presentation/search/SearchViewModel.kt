package com.cinevault.app.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinevault.app.common.util.Resource
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val results: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

/**
 * Classic "search as you type" pattern: debounce so we don't hit the
 * network on every keystroke, distinctUntilChanged so repeats don't
 * re-trigger, and flatMapLatest so an in-flight search gets cancelled
 * the moment a newer query comes in.
 */
@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovies: SearchMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val queryFlow = MutableStateFlow("")

    init {
        queryFlow
            .debounce(350)
            .distinctUntilChanged()
            .flatMapLatest { query -> searchMovies(query) }
            .onEach { resource ->
                _uiState.value = when (resource) {
                    is Resource.Loading -> _uiState.value.copy(isLoading = true, errorMessage = null)
                    is Resource.Success -> _uiState.value.copy(
                        results = resource.data,
                        isLoading = false,
                        errorMessage = null
                    )
                    is Resource.Error -> _uiState.value.copy(isLoading = false, errorMessage = resource.message)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(newQuery: String) {
        _uiState.value = _uiState.value.copy(query = newQuery)
        queryFlow.value = newQuery
    }
}
