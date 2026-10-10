package com.cinevault.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cinevault.app.domain.repository.AccountRepository
import com.cinevault.app.presentation.navigation.CineVaultNavGraph
import com.cinevault.app.presentation.theme.CineVaultTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var accountRepository: AccountRepository

    private val authApprovedFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        handleDeepLinkIntent(intent)

        setContent {
            val darkMode by accountRepository.darkModeEnabled.collectAsState(initial = true)
            CineVaultTheme(darkTheme = darkMode) {
                CineVaultNavGraph(authApprovedFlow = authApprovedFlow)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleDeepLinkIntent(intent)
    }

    private fun handleDeepLinkIntent(intent: Intent?) {
        val data = intent?.data
        if (data != null && data.scheme == "cinevault" && data.host == "approved") {
            authApprovedFlow.tryEmit(Unit)
        }
    }
}
