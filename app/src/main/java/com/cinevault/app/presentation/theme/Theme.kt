package com.cinevault.app.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceVariant,
    onBackground = OnBackground,
    onSurface = OnBackground,
    primary = Accent,
    onPrimary = Background,
    error = ErrorRed
)

private val LightColors = lightColorScheme(
    primary = Accent,
    onPrimary = Background
)

@Composable
fun CineVaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = CineVaultTypography,
        content = content
    )
}
