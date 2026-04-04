package io.github.daiji256.showcase.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowcaseTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> when {
            isSystemInDarkTheme() -> dynamicDarkColorScheme(LocalContext.current)
            else -> dynamicLightColorScheme(LocalContext.current)
        }

        else -> when {
            isSystemInDarkTheme() -> darkColorScheme()
            else -> lightColorScheme()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
