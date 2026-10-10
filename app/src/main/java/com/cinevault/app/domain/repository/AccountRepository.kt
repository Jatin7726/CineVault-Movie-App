package com.cinevault.app.domain.repository

import com.cinevault.app.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    val sessionId: Flow<String?>
    val accountId: Flow<Int?>
    val username: Flow<String?>
    val name: Flow<String?>

    val guestSessionId: Flow<String?>
    val isGuest: Flow<Boolean>

    val darkModeEnabled: Flow<Boolean>
    val notificationsEnabled: Flow<Boolean>
    val biometricLockEnabled: Flow<Boolean>
    val language: Flow<String>

    suspend fun createRequestToken(): String
    suspend fun createSession(requestToken: String): String
    suspend fun createGuestSession(): String
    suspend fun fetchAndSaveAccountDetails(sessionId: String): Account
    suspend fun logout()

    suspend fun setDarkMode(enabled: Boolean)
    suspend fun setNotifications(enabled: Boolean)
    suspend fun setBiometricLock(enabled: Boolean)
    suspend fun setLanguage(lang: String)
}
