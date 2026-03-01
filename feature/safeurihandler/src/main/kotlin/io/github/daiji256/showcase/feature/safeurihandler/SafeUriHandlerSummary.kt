package io.github.daiji256.showcase.feature.safeurihandler

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import io.github.daiji256.showcase.core.ui.navigation.Navigator

val SafeUriHandlerSummary = object : FeatureSummary {
    override val title: String
        @Composable get() = stringResource(id = R.string.feature_safe_uri_handler_title)

    override fun Navigator<NavKey>.navigate(): Boolean =
        navigate(route = SafeUriHandlerNavKey)
}
