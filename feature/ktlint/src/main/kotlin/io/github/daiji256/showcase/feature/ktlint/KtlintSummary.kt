package io.github.daiji256.showcase.feature.ktlint

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val ktlintSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_ktlint_title),
        navKey = KtlintNavKey,
    )
