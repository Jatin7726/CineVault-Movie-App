package com.cinevault.app.data.repository

import com.cinevault.app.data.local.SessionManager
import com.cinevault.app.data.remote.TmdbApi
import com.cinevault.app.data.remote.dto.CreateSessionRequestDto
import com.cinevault.app.domain.model.Account
import com.cinevault.app.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val api: TmdbApi,
    private val sessionManager: SessionManager
) : AccountRepository {

    override val sessionId: Flow<String?> = sessionManager.sessionId
    override val accountId: Flow<Int?> = sessionManager.accountId
    override val username: Flow<String?> = sessionManager.username
    override val name: Flow<String?> = sessionManager.name

    override val guestSessionId: Flow<String?> = sessionManager.guestSessionId
    override val isGuest: Flow<Boolean> = sessionManager.isGuest

    override val darkModeEnabled: Flow<Boolean> = sessionManager.darkModeEnabled
    override val notificationsEnabled: Flow<Boolean> = sessionManager.notificationsEnabled
    override val biometricLockEnabled: Flow<Boolean> = sessionManager.biometricLockEnabled
    override val language: Flow<String> = sessionManager.language

    override suspend fun createRequestToken(): String {
        val response = api.createRequestToken()
        return response.requestToken
    }

    override suspend fun createSession(requestToken: String): String {
        val response = api.createSession(CreateSessionRequestDto(requestToken))
        return response.sessionId
    }

    override suspend fun createGuestSession(): String {
        val response = api.createGuestSession()
        sessionManager.saveGuestSession(response.guestSessionId)
        return response.guestSessionId
    }

    override suspend fun fetchAndSaveAccountDetails(sessionId: String): Account {
        val dto = api.getAccountDetails(sessionId)
        val displayName = dto.name.ifBlank { dto.username }
        sessionManager.saveSession(
            sessionId = sessionId,
            accountId = dto.id,
            username = dto.username,
            name = displayName
        )
        return Account(
            id = dto.id,
            name = displayName,
            username = dto.username,
            language = dto.language,
            country = dto.country
        )
    }

    override suspend fun logout() {
        sessionManager.clearSession()
    }

    override suspend fun setDarkMode(enabled: Boolean) = sessionManager.setDarkMode(enabled)
    override suspend fun setNotifications(enabled: Boolean) = sessionManager.setNotifications(enabled)
    override suspend fun setBiometricLock(enabled: Boolean) = sessionManager.setBiometricLock(enabled)
    override suspend fun setLanguage(lang: String) = sessionManager.setLanguage(lang)
}
