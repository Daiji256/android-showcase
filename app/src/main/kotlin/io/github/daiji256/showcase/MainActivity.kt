package io.github.daiji256.showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalUriHandler
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseTheme
import io.github.daiji256.showcase.core.ui.urihandler.SafeUriHandler
import io.github.daiji256.showcase.feature.ktlint.KtlintScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uriHandler = SafeUriHandler(this)
        setContent {
            CompositionLocalProvider(
                LocalUriHandler provides uriHandler,
            ) {
                ShowcaseTheme {
                    KtlintScreen()
                }
            }
        }
    }
}
