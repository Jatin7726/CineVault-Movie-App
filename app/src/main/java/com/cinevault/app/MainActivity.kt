package com.cinevault.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cinevault.app.presentation.navigation.CineVaultNavGraph
import com.cinevault.app.presentation.theme.CineVaultTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authApprovedFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        handleDeepLinkIntent(intent)

        setContent {
            CineVaultTheme {
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
