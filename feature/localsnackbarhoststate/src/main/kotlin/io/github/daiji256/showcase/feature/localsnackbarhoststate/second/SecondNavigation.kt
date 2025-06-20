package io.github.daiji256.showcase.feature.localsnackbarhoststate.second

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object SecondScreenRoute

internal fun NavGraphBuilder.secondScreen(navController: NavController) {
    composable<SecondScreenRoute> {
        SecondScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

internal fun NavController.navigateToSecondScreen() = navigate(route = SecondScreenRoute)
