package io.github.daiji256.showcase.core.ui.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavController

@Immutable
interface FeatureSummary {
    @Composable
    fun title(): String
    fun NavController.navigate()
}
