package io.github.daiji256.showcase.feature.ktlint

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

data class KtlintSummary(val navController: NavController) : FeatureSummary {
    override val title: String
        @Composable get() = stringResource(id = R.string.feature_ktlint_title)

    override fun navigate() = navController.navigateToKtlintScreen()
}
