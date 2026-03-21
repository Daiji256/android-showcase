package io.github.daiji256.showcase.feature.navnode

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val NavNodeSummary = object : FeatureSummary {
    override val navKey: NavKey = NavNodeNavKey

    override val title: String
        @Composable get() = stringResource(id = R.string.feature_nav_node_title)
}
