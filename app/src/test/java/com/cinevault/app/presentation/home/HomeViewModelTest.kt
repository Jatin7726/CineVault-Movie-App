package com.cinevault.app.presentation.home

import app.cash.turbine.test
import com.cinevault.app.common.util.Resource
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.domain.repository.MovieRepository
import com.cinevault.app.domain.usecase.GetPopularMoviesUseCase
import com.cinevault.app.domain.usecase.GetTrendingMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * A minimal fake repository - no Retrofit, no Room, no Android
 * framework classes involved. This is exactly why the ViewModel only
 * depends on use cases backed by an interface: it's trivial to test.
 */
private class FakeMovieRepository(
    private val trending: List<Movie>
) : MovieRepository {
    override fun getTrendingMovies(): Flow<Resource<List<Movie>>> =
        flowOf(Resource.Loading(), Resource.Success(trending))

    override fun getPopularMovies() = flowOf(androidx.paging.PagingData.empty<Movie>())
    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> = flowOf(Resource.Success(emptyList()))
    override fun getMovieDetail(movieId: Int): Flow<Resource<com.cinevault.app.domain.model.MovieDetail>> =
        flowOf(Resource.Error("Not implemented"))
    override fun getWatchlist(): Flow<List<Movie>> = flowOf(emptyList())
    override suspend fun toggleWatchlist(movie: Movie) {}
}

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() { Dispatchers.setMain(testDispatcher) }

    @After
    fun tearDown() { Dispatchers.resetMain() }

    @Test
    fun `trending movies load into ui state`() = runTest {
        val fakeMovies = listOf(
            Movie(1, "Dune: Part Two", "overview", null, null, "2024-03-01", 8.4)
        )
        val repository = FakeMovieRepository(fakeMovies)
        val viewModel = HomeViewModel(
            getTrendingMovies = GetTrendingMoviesUseCase(repository),
            getPopularMovies = GetPopularMoviesUseCase(repository)
        )

        viewModel.uiState.test {
            awaitItem() // initial state
            awaitItem() // loading = true
            val loaded = awaitItem()
            assertEquals(fakeMovies, loaded.trending)
            assertEquals(false, loaded.isLoadingTrending)
        }
    }
}
