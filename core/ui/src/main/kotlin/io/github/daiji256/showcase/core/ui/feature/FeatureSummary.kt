package io.github.daiji256.showcase.core.ui.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Stable
interface FeatureSummary {
    val title: String @Composable get

    fun navigate()
}
