package com.cinevault.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cinevault.app.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class CineVaultDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "cinevault.db"
    }
}
