package io.github.daiji256.sandbox.core.designsystem.theme

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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

    DisposableEffect(darkTheme) {
        val activity = context.findComponentActivity()
        activity?.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
                detectDarkMode = { darkTheme },
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = lightScrim,
                darkScrim = darkScrim,
                detectDarkMode = { darkTheme },
            ),
        )
        onDispose {}
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}

private fun Context.findComponentActivity(): ComponentActivity? =
    when (this) {
        is ComponentActivity -> this
        is ContextWrapper -> baseContext.findComponentActivity()
        else -> null
    }

// https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-37;drc=a6c7b89d5f80802f23af1105b919bafb01d46e2d
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

// https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=39-42;drc=a6c7b89d5f80802f23af1105b919bafb01d46e2d
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
