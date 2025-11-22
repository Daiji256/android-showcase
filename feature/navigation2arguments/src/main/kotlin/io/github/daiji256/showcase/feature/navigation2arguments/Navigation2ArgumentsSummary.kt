package io.github.daiji256.showcase.feature.navigation2arguments

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val navigation2ArgumentsSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_nav2_args_title),
        navKey = Navigation2ArgumentsNavKey,
    )
