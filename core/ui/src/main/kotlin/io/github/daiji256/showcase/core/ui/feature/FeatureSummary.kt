package io.github.daiji256.showcase.core.ui.feature

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.navigation.Navigator

interface FeatureSummary {
    val title: String
        @Composable get

    fun Navigator<NavKey>.navigate(): Boolean
}
