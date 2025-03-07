package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

data class RoborazziSummary(val navController: NavController) : FeatureSummary {
    override val title: String
        @Composable get() = stringResource(id = R.string.feature_roborazzi_title)

    override fun navigate() = navController.navigateToRoborazziScreen()
}
