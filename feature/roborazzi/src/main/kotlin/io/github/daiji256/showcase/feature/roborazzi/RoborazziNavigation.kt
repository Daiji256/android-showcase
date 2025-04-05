package io.github.daiji256.showcase.feature.roborazzi

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object RoborazziScreenRoute

fun NavGraphBuilder.roborazziScreen(navController: NavController) {
    composable<RoborazziScreenRoute> {
        RoborazziScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

fun NavController.navigateToRoborazziScreen() = navigate(route = RoborazziScreenRoute)
