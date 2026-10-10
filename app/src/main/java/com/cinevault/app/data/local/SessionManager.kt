package com.cinevault.app.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val SESSION_KEY = stringPreferencesKey("session_id")
    private val ACCOUNT_ID_KEY = intPreferencesKey("account_id")
    private val USERNAME_KEY = stringPreferencesKey("username")
    private val NAME_KEY = stringPreferencesKey("name")

    private val GUEST_SESSION_KEY = stringPreferencesKey("guest_session_id")
    private val IS_GUEST_KEY = booleanPreferencesKey("is_guest")

    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    private val NOTIFICATIONS_KEY = booleanPreferencesKey("notifications")
    private val BIOMETRIC_LOCK_KEY = booleanPreferencesKey("biometric_lock")
    private val LANGUAGE_KEY = stringPreferencesKey("language")

    val sessionId: Flow<String?> = dataStore.data.map { it[SESSION_KEY] }
    val accountId: Flow<Int?> = dataStore.data.map { it[ACCOUNT_ID_KEY] }
    val username: Flow<String?> = dataStore.data.map { it[USERNAME_KEY] }
    val name: Flow<String?> = dataStore.data.map { it[NAME_KEY] }

    val guestSessionId: Flow<String?> = dataStore.data.map { it[GUEST_SESSION_KEY] }
    val isGuest: Flow<Boolean> = dataStore.data.map { it[IS_GUEST_KEY] ?: false }

    val darkModeEnabled: Flow<Boolean> = dataStore.data.map { it[DARK_MODE_KEY] ?: true }
    val notificationsEnabled: Flow<Boolean> = dataStore.data.map { it[NOTIFICATIONS_KEY] ?: true }
    val biometricLockEnabled: Flow<Boolean> = dataStore.data.map { it[BIOMETRIC_LOCK_KEY] ?: true }
    val language: Flow<String> = dataStore.data.map { it[LANGUAGE_KEY] ?: "English" }

    suspend fun saveSession(sessionId: String, accountId: Int, username: String, name: String) {
        dataStore.edit { prefs ->
            prefs[SESSION_KEY] = sessionId
            prefs[ACCOUNT_ID_KEY] = accountId
            prefs[USERNAME_KEY] = username
            prefs[NAME_KEY] = name
            prefs[IS_GUEST_KEY] = false
            prefs.remove(GUEST_SESSION_KEY)
        }
    }

    suspend fun saveGuestSession(guestSessionId: String) {
        dataStore.edit { prefs ->
            prefs[GUEST_SESSION_KEY] = guestSessionId
            prefs[IS_GUEST_KEY] = true
            prefs.remove(SESSION_KEY)
            prefs.remove(ACCOUNT_ID_KEY)
            prefs.remove(USERNAME_KEY)
            prefs.remove(NAME_KEY)
        }
    }

    suspend fun clearSession() {
        dataStore.edit { prefs ->
            prefs.remove(SESSION_KEY)
            prefs.remove(ACCOUNT_ID_KEY)
            prefs.remove(USERNAME_KEY)
            prefs.remove(NAME_KEY)
            prefs.remove(GUEST_SESSION_KEY)
            prefs[IS_GUEST_KEY] = false
        }
    }

    suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { it[DARK_MODE_KEY] = enabled }
    }

    suspend fun setNotifications(enabled: Boolean) {
        dataStore.edit { it[NOTIFICATIONS_KEY] = enabled }
    }

    suspend fun setBiometricLock(enabled: Boolean) {
        dataStore.edit { it[BIOMETRIC_LOCK_KEY] = enabled }
    }

    suspend fun setLanguage(lang: String) {
        dataStore.edit { it[LANGUAGE_KEY] = lang }
    }
}
