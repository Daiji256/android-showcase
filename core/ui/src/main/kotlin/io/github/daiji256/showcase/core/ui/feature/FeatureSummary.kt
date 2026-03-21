package io.github.daiji256.showcase.core.ui.feature

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey

interface FeatureSummary {
    val navKey: NavKey

    val title: String
        @Composable get
}
