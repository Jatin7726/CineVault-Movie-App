package com.cinevault.app.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.cinevault.app.presentation.common.MoviePosterCard

/**
 * Trending uses a plain StateFlow (small, fixed list).
 * Popular uses Paging 3 (large, infinitely scrollable list) - deliberately
 * showing both patterns in one screen since interviewers like to probe
 * "when would you use Paging vs a plain list?".
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMovieClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val popularItems = viewModel.popularMovies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "CineVault",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header + trending row spans both grid columns
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column {
                    Text(
                        text = "Trending Today",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Spacer(Modifier.height(10.dp))

                    if (uiState.isLoadingTrending && uiState.trending.isEmpty()) {
                        Box(Modifier.height(170.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    } else {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(uiState.trending, key = { it.id }) { movie ->
                                MoviePosterCard(
                                    movie = movie,
                                    onClick = onMovieClick,
                                    modifier = Modifier.width(118.dp)
                                )
                            }
                        }
                    }

                    uiState.errorMessage?.let { message ->
                        Text(
                            text = message,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Popular Movies",
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Spacer(Modifier.height(4.dp))
                }
            }

            // Popular grid, backed directly by Paging 3
            items(
                count = popularItems.itemCount,
                key = popularItems.itemKey { it.id }
            ) { index ->
                popularItems[index]?.let { movie ->
                    MoviePosterCard(
                        movie = movie,
                        onClick = onMovieClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
