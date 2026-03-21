package io.github.daiji256.showcase.feature.customtabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val CustomTabsSummary = object : FeatureSummary {
    override val navKey: NavKey = CustomTabsNavKey

    override val title: String
        @Composable get() = stringResource(id = R.string.feature_custom_tabs_title)
}
