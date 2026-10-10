package com.cinevault.app.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cinevault.app.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE isTrending = 1 ORDER BY cachedAt DESC")
    fun observeTrending(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isPopular = 1 ORDER BY cachedAt DESC")
    fun pagingSourcePopular(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE isInWatchlist = 1")
    fun observeWatchlist(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    suspend fun getById(movieId: Int): MovieEntity?

    @Update
    suspend fun update(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE isPopular = 1")
    suspend fun clearPopular()
}
