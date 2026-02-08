package io.github.daiji256.showcase.feature.navnode

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val navNodeSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_nav_node_title),
        navKey = NavNodeNavKey,
    )
