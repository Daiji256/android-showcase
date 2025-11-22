package io.github.daiji256.showcase.feature.safeurihandler

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val safeUriHandlerSummary
    @Composable get() = FeatureSummary(
        title = stringResource(id = R.string.feature_safe_uri_handler_title),
        navKey = SafeUriHandlerNavKey,
    )
