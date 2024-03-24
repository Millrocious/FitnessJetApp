package com.millrocious.fitness_jet_app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColorScheme(
    primary = Blue80,
    onPrimary = Blue20,
    primaryContainer = Blue80,
    onPrimaryContainer = Blue90,
    inversePrimary = Blue40, //Blue40
    secondary = DarkBlue80,
    onSecondary = DarkBlue20,
    secondaryContainer = DarkBlue80,
    onSecondaryContainer = Grey20,
    tertiary = Orange80,
    onTertiary = Orange20,
    tertiaryContainer = Orange30,
    onTertiaryContainer = Orange90,
    error = SoftPastelRed80,
    onError = SoftPastelRed20,
    errorContainer = DarkSoftPastelRed50,
    onErrorContainer = SoftPastelRed70,
    background = Grey10,
    onBackground = Grey90,
    surface = Grey13,
    surfaceContainer = Grey20,
    onSurface = Grey80,
    inverseSurface = Grey90,
    inverseOnSurface = Grey10,
    surfaceVariant = Grey15,
    onSurfaceVariant = Grey80,
    outline = Grey50
)

private val LightColorPalette = lightColorScheme(
    primary = Blue40,
    onPrimary = Color.White,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    inversePrimary = Blue80,
    secondary = DarkBlue40,
    onSecondary = Color.White,
    secondaryContainer = DarkBlue90,
    onSecondaryContainer = DarkBlue10,
    tertiary = Orange40,
    onTertiary = Color.White,
    tertiaryContainer = Orange90,
    onTertiaryContainer = Orange10,
    error = SoftPastelRed40,
    onError = Color.White,
    errorContainer = SoftPastelRed80,
    onErrorContainer = DarkSoftPastelRed50,
    background = Grey99,
    onBackground = Grey10,
    surface = Grey90,
    onSurface = Grey30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = Grey95,
    onSurfaceVariant = Grey30,
    outline = Grey60
)

@Composable
fun SportAndHealthManagerTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        // Dynamic color is available on Android 12+
        dynamicColor: Boolean = true,
        content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
    )
}