package io.github.daiji256.showcase.core.ui.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
interface FeatureSummary {
    @Composable
    fun title(): String

    val navigateCallback: () -> Unit @Composable get
}
