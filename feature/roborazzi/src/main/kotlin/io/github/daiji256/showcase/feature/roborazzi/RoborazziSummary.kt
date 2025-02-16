package io.github.daiji256.showcase.feature.roborazzi

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import io.github.daiji256.showcase.core.ui.navigation.LocalNavController

data object RoborazziSummary : FeatureSummary {
    @Composable
    override fun title(): String = stringResource(id = R.string.feature_roborazzi_title)

    override val navigateCallback: () -> Unit
        @Composable get() = LocalNavController.current.run {
            dropUnlessResumed { navigateToRoborazziScreen() }
        }
}
