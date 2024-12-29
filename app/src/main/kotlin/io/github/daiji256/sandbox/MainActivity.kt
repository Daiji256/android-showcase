package io.github.daiji256.sandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalUriHandler
import io.github.daiji256.sandbox.core.designsystem.theme.SandboxTheme
import io.github.daiji256.sandbox.core.ui.urihandler.SafeUriHandler
import io.github.daiji256.sandbox.feature.ktlint.KtlintScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uriHandler = SafeUriHandler(this)
        setContent {
            CompositionLocalProvider(
                LocalUriHandler provides uriHandler,
            ) {
                SandboxTheme {
                    KtlintScreen()
                }
            }
        }
    }
}
