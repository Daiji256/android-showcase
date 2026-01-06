package io.github.daiji256.showcase.feature.license

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val licenseSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_license_title),
        navKey = LicenseNavKey,
    )
