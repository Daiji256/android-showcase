package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

val RoborazziSummary = object : FeatureSummary {
    override val navKey: NavKey = RoborazziNavKey

    override val title: String
        @Composable get() = stringResource(id = R.string.feature_roborazzi_title)
}
