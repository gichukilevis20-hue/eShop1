package com.example.eshop.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = Blue80,
    onPrimaryContainer = Blue20,
    secondary = Teal40,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    secondaryContainer = Teal80,
    onSecondaryContainer = Teal40,
    error = Red40,
    onError = androidx.compose.ui.graphics.Color.White,
    background = Gray10,
    onBackground = Gray900,
    surface = androidx.compose.ui.graphics.Color.White,
    onSurface = Gray900,
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue80,
    onPrimary = Blue20,
    primaryContainer = Blue40,
    onPrimaryContainer = Blue80,
    secondary = Teal80,
    onSecondary = Teal40,
    secondaryContainer = Teal40,
    onSecondaryContainer = Teal80,
    error = Red80,
    onError = Red40,
)

@Composable
fun EShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
