package com.example.profilcompose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2563EB),
    secondary = Color(0xFF52647D),
    tertiary = Color(0xFF008B9A),

    background = Color(0xFFF8FAFC),
    surface = Color(0xFFE8EDF5),
    onSurface = Color(0xFF465468),

    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF172033),

    primaryContainer = Color(0xFFDBEAFE),
    onPrimaryContainer = Color(0xFF1E3A8A),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8AB4FF),
    secondary = Color(0xFFB5C5DC),
    tertiary = Color(0xFF80D5DF),

    primaryContainer = Color(0xFF1E3A8A),

    background = Color(0xFF101411),
    surface = Color(0xFF1B2638),
    onSurface = Color(0xFFE5ECF6),

    onPrimary = Color(0xFF003064),
    onSecondary = Color(0xFF263448),
    onTertiary = Color(0xFF00363D),
    onBackground = Color(0xFFE0E4DF),
    onPrimaryContainer = Color(0xFFDBEAFE),
)

private val PShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun ProfilComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = PTypography,
        shapes = PShapes,
        content = content
    )
}