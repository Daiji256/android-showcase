package io.github.daiji256.showcase.feature.ktlint

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object KtlintScreenRoute

fun NavGraphBuilder.ktlintScreen() {
    composable<KtlintScreenRoute> {
        KtlintScreen()
    }
}

fun NavController.navigateToKtlintScreen() = navigate(route = KtlintScreenRoute)
