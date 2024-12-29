package io.github.daiji256.sandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.daiji256.sandbox.core.common.Sample
import io.github.daiji256.sandbox.core.designsystem.theme.SandboxTheme
import io.github.daiji256.sandbox.feature.ktlint.KtlintScreen

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
