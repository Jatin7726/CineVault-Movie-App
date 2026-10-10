package com.cinevault.app.data.remote

import com.cinevault.app.data.remote.dto.AccountDto
import com.cinevault.app.data.remote.dto.CreateGuestSessionResponseDto
import com.cinevault.app.data.remote.dto.CreateSessionRequestDto
import com.cinevault.app.data.remote.dto.CreateSessionResponseDto
import com.cinevault.app.data.remote.dto.MovieDetailDto
import com.cinevault.app.data.remote.dto.MovieResponseDto
import com.cinevault.app.data.remote.dto.RequestTokenResponseDto
import com.cinevault.app.data.remote.dto.VideoResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Pure Retrofit interface - the actual Bearer header is added by
 * AuthInterceptor, so this file only describes endpoints and params.
 */
interface TmdbApi {

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(): MovieResponseDto

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieResponseDto

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String): MovieResponseDto

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") movieId: Int): MovieDetailDto

    @GET("movie/{id}/videos")
    suspend fun getMovieVideos(@Path("id") movieId: Int): VideoResponseDto

    @GET("authentication/token/new")
    suspend fun createRequestToken(): RequestTokenResponseDto

    @POST("authentication/session/new")
    suspend fun createSession(@Body body: CreateSessionRequestDto): CreateSessionResponseDto

    @GET("authentication/guest_session/new")
    suspend fun createGuestSession(): CreateGuestSessionResponseDto

    @GET("account")
    suspend fun getAccountDetails(@Query("session_id") sessionId: String): AccountDto

    @GET("account/{account_id}")
    suspend fun getAccountDetailsById(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String
    ): AccountDto

    @GET("account/{account_id}/watchlist/movies")
    suspend fun getAccountWatchlistMovies(
        @Path("account_id") accountId: Int,
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "created_at.desc"
    ): MovieResponseDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}
