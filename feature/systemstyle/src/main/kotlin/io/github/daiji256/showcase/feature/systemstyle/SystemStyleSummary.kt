package io.github.daiji256.showcase.feature.systemstyle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

@Stable
class SystemStyleSummary(private val navController: NavController) : FeatureSummary {
    override val title: String
        @Composable get() = stringResource(id = R.string.feature_system_style_title)

    override fun navigate() = navController.navigateToSystemStyleScreen()
}
