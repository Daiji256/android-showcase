package io.github.daiji256.showcase.feature.localsnackbarhoststate

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import io.github.daiji256.showcase.core.ui.navigation.Navigator
import io.github.daiji256.showcase.feature.localsnackbarhoststate.first.LocalSnackbarHostStateFirstNavKey

val LocalSnackbarHostStateSummary = object : FeatureSummary {
    override val title: String
        @Composable get() = stringResource(id = R.string.feature_lshs_title)

    override fun Navigator<NavKey>.navigate(): Boolean =
        navigate(route = LocalSnackbarHostStateFirstNavKey)
}
