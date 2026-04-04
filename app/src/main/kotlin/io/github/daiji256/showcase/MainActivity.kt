package io.github.daiji256.showcase

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        lightScrim = android.graphics.Color.TRANSPARENT,
                        darkScrim = android.graphics.Color.TRANSPARENT,
                        detectDarkMode = { it.configuration.isSystemInDarkTheme },
                    ),
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim = lightScrim,
                        darkScrim = darkScrim,
                        detectDarkMode = { it.configuration.isSystemInDarkTheme },
                    ),
                )
            }
        }

        setContent {
            MainContent()
        }
    }

    private val Configuration.isSystemInDarkTheme: Boolean
        get() = (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

    // https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-37;drc=a6c7b89d5f80802f23af1105b919bafb01d46e2d
    private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

    // https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=39-42;drc=a6c7b89d5f80802f23af1105b919bafb01d46e2d
    private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
}
