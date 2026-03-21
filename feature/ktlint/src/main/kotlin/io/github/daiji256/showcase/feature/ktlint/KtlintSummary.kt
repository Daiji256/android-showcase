package io.github.daiji256.showcase.feature.ktlint

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val KtlintSummary = object : FeatureSummary {
    override val navKey: NavKey = KtlintNavKey

    override val title: String
        @Composable get() = stringResource(id = R.string.feature_ktlint_title)
}
