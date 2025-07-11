package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object HiltComposableScreenRoute

fun NavGraphBuilder.hiltComposableScreen(navController: NavController) {
    composable<HiltComposableScreenRoute> {
        HiltComposableScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

fun NavController.navigateToHiltComposableScreen() = navigate(route = HiltComposableScreenRoute)
