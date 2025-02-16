package io.github.daiji256.showcase.feature.ktlint

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary

data object KtlintSummary : FeatureSummary {
    @Composable
    override fun title(): String = stringResource(id = R.string.feature_ktlint_title)
    override fun NavController.navigate() = navigateToKtlintScreen()
}
