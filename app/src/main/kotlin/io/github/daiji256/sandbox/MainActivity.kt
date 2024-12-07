package io.github.daiji256.sandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.daiji256.core.common.Sample
import io.github.daiji256.feature.ktlint.KtlintScreen
import io.github.daiji256.sandbox.ui.theme.SandboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Delete Sample
        Sample()
        setContent {
            SandboxTheme {
                KtlintScreen()
            }
        }
    }
}
