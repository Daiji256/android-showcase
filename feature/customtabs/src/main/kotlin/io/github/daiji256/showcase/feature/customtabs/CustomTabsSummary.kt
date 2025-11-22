package io.github.daiji256.showcase.feature.customtabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val customTabsSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_custom_tabs_title),
        navKey = CustomTabsNavKey,
    )
