package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val HiltComposableSummary = object : FeatureSummary {
    override val navKey: NavKey = HiltComposableNavKey

    override val title: String
        @Composable get() = stringResource(id = R.string.feature_hilt_comp_title)
}
