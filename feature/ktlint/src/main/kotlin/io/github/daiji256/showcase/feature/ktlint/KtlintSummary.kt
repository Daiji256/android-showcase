package io.github.daiji256.showcase.feature.ktlint

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.dropUnlessResumed
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import io.github.daiji256.showcase.core.ui.navigation.LocalNavController

data object KtlintSummary : FeatureSummary {
    override val title: String
        @Composable get() = stringResource(id = R.string.feature_ktlint_title)

    override val navigateCallback: () -> Unit
        @Composable get() = LocalNavController.current.run {
            dropUnlessResumed { navigateToKtlintScreen() }
        }
}
