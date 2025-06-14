package io.github.daiji256.showcase.feature.safeurihandler

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object SafeUriHandlerScreenRoute

fun NavGraphBuilder.safeUriHandlerScreen(navController: NavController) {
    composable<SafeUriHandlerScreenRoute> {
        SafeUriHandlerScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

fun NavController.navigateToSafeUriHandlerScreen() = navigate(route = SafeUriHandlerScreenRoute)
