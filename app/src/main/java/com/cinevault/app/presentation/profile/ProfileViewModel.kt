package com.cinevault.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinevault.app.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val isLoggedIn: Boolean = false,
    val isGuest: Boolean = false,
    val name: String = "Jatin Rastogi",
    val emailOrUsername: String = "jatin@example.com",
    val darkModeEnabled: Boolean = true,
    val notificationsEnabled: Boolean = true,
    val biometricLockEnabled: Boolean = true,
    val language: String = "English",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val pendingRequestToken: String? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _pendingRequestToken = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<ProfileUiState> = combine(
        accountRepository.sessionId,
        accountRepository.isGuest,
        accountRepository.name,
        accountRepository.username,
        accountRepository.darkModeEnabled,
        accountRepository.notificationsEnabled,
        accountRepository.biometricLockEnabled,
        accountRepository.language,
        _pendingRequestToken,
        _isLoading,
        _errorMessage
    ) { values ->
        val sessionId = values[0] as String?
        val isGuest = values[1] as Boolean
        val name = values[2] as String?
        val username = values[3] as String?
        val darkMode = values[4] as Boolean
        val notifications = values[5] as Boolean
        val biometrics = values[6] as Boolean
        val lang = values[7] as String
        val pendingToken = values[8] as String?
        val loading = values[9] as Boolean
        val error = values[10] as String?

        val isLoggedIn = !sessionId.isNullOfEmpty()
        val displayName = when {
            isLoggedIn && !name.isNullOrBlank() -> name
            isGuest -> "Guest User"
            else -> "Jatin Rastogi"
        }
        val displayHandle = when {
            isLoggedIn && !username.isNullOrBlank() -> "@$username"
            isGuest -> "Guest Session (expires in 60 mins)"
            else -> "jatin@example.com"
        }

        ProfileUiState(
            isLoggedIn = isLoggedIn,
            isGuest = isGuest,
            name = displayName,
            emailOrUsername = displayHandle,
            darkModeEnabled = darkMode,
            notificationsEnabled = notifications,
            biometricLockEnabled = biometrics,
            language = lang,
            isLoading = loading,
            errorMessage = error,
            pendingRequestToken = pendingToken
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileUiState()
    )

    fun startLogin(onLaunchUrl: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val token = accountRepository.createRequestToken()
                _pendingRequestToken.value = token
                _isLoading.value = false
                val authUrl = "https://www.themoviedb.org/authenticate/$token?redirect_to=cinevault://approved"
                onLaunchUrl(authUrl)
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = e.localizedMessage ?: "Failed to start login flow"
            }
        }
    }

    fun startGuestSession() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                accountRepository.createGuestSession()
                _pendingRequestToken.value = null
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = e.localizedMessage ?: "Failed to create guest session"
            }
        }
    }

    fun completeLoginAfterApproval() {
        val token = _pendingRequestToken.value ?: return
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val sessionId = accountRepository.createSession(token)
                accountRepository.fetchAndSaveAccountDetails(sessionId)
                _pendingRequestToken.value = null
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _errorMessage.value = e.localizedMessage ?: "Failed to create session. Please approve in browser."
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            accountRepository.logout()
            _pendingRequestToken.value = null
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch { accountRepository.setDarkMode(enabled) }
    }

    fun toggleNotifications(enabled: Boolean) {
        viewModelScope.launch { accountRepository.setNotifications(enabled) }
    }

    fun toggleBiometricLock(enabled: Boolean) {
        viewModelScope.launch { accountRepository.setBiometricLock(enabled) }
    }

    fun setLanguage(lang: String) {
        viewModelScope.launch { accountRepository.setLanguage(lang) }
    }
}

private fun String?.isNullOfEmpty(): Boolean = this == null || this.trim().isEmpty()
