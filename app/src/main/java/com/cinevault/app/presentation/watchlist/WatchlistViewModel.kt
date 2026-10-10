package com.cinevault.app.presentation.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.usecase.GetWatchlistUseCase
import com.cinevault.app.domain.usecase.ToggleWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    getWatchlist: GetWatchlistUseCase,
    private val toggleWatchlist: ToggleWatchlistUseCase
) : ViewModel() {

    val watchlist: StateFlow<List<Movie>> = getWatchlist()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun remove(movie: Movie) {
        viewModelScope.launch { toggleWatchlist(movie) }
    }
}
