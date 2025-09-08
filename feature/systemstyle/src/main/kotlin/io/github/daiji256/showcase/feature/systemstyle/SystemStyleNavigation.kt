package io.github.daiji256.showcase.feature.systemstyle

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object SystemStyleScreenRoute

fun NavGraphBuilder.systemStyleScreen(navController: NavController) {
    composable<SystemStyleScreenRoute> {
        SystemStyleScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

fun NavController.navigateToSystemStyleScreen() = navigate(route = SystemStyleScreenRoute)
