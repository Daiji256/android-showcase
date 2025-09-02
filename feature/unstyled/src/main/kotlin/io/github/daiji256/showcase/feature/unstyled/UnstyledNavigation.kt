package io.github.daiji256.showcase.feature.unstyled

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object UnstyledScreenRoute

fun NavGraphBuilder.unstyledScreen(navController: NavController) {
    composable<UnstyledScreenRoute> {
        UnstyledScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

fun NavController.navigateToUnstyledScreen() = navigate(route = UnstyledScreenRoute)
