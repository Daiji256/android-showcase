package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val hiltComposableSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_hilt_comp_title),
        navKey = HiltComposableNavKey,
    )
