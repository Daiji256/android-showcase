package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

data object RoborazziSummary : FeatureSummary {
    @Composable
    override fun title(): String = stringResource(id = R.string.feature_roborazzi_title)
    override fun NavController.navigate() = navigateToRoborazziScreen()
}
