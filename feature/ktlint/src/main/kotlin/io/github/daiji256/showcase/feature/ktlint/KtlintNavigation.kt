package io.github.daiji256.showcase.feature.ktlint

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object KtlintScreenRoute

fun NavGraphBuilder.ktlintScreen(navController: NavController) {
    composable<KtlintScreenRoute> {
        KtlintScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

fun NavController.navigateToKtlintScreen() = navigate(route = KtlintScreenRoute)
