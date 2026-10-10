package com.cinevault.app.common.util

/**
 * Generic wrapper so the UI layer always knows whether data is
 * loading, loaded, or failed - used for every Flow coming out of
 * the repository (offline-first: Loading -> cached data -> fresh data / Error).
 */
sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String, val cachedData: Any? = null) : Resource<Nothing>()
    data class Loading(val isLoading: Boolean = true) : Resource<Nothing>()
}
