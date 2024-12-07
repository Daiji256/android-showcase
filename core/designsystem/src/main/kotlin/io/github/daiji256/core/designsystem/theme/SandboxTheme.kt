package io.github.daiji256.core.designsystem.theme

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
fun SandboxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> when {
            darkTheme -> dynamicDarkColorScheme(context)
            else -> dynamicLightColorScheme(context)
        }

        else -> when {
            darkTheme -> darkColorScheme()
            else -> lightColorScheme()
        }
    }

    // TODO: enableEdgeToEdge

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
