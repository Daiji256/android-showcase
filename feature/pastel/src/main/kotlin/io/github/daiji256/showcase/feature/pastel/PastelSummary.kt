package io.github.daiji256.showcase.feature.pastel

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val PastelSummary = object : FeatureSummary {
    override val navKey: NavKey = PastelNavKey

    override val title: String
        @Composable get() = stringResource(id = R.string.feature_pastel_title)
}
