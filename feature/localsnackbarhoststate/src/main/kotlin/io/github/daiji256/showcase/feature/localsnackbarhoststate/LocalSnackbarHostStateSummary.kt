package io.github.daiji256.showcase.feature.localsnackbarhoststate

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val localSnackbarHostStateSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_lshs_title),
        navKey = LocalSnackbarHostStateNavKey,
    )
