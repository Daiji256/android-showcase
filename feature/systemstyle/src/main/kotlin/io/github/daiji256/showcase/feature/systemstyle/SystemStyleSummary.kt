package io.github.daiji256.showcase.feature.systemstyle

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val systemStyleSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_system_style_title),
        navKey = SystemStyleNavKey,
    )
