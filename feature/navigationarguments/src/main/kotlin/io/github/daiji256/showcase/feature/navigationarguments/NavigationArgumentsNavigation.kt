package io.github.daiji256.showcase.feature.navigationarguments

import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object NavigationArgumentsScreenRoute

fun NavGraphBuilder.navigationArgumentsScreen(navController: NavController) {
    composable<NavigationArgumentsScreenRoute> {
        NavigationArgumentsScreen(
            onNavigateUpClick = dropUnlessResumed(block = navController::navigateUp),
        )
    }
}

fun NavController.navigateToNavigationArgumentsScreen() =
    navigate(route = NavigationArgumentsScreenRoute)
