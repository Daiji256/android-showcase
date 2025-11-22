package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val roborazziSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_roborazzi_title),
        navKey = RoborazziNavKey,
    )
