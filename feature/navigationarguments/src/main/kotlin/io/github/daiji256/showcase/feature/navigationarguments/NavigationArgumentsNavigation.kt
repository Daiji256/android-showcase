package io.github.daiji256.showcase.feature.navigationarguments

import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable
internal data object NavigationArgumentsNavGraphRoute

fun NavGraphBuilder.navigationArgumentsNavGraph(navController: NavController) {
    navigation<NavigationArgumentsNavGraphRoute>(
        startDestination = NavigationArgumentsScreenRoute,
    ) {
        navigationArgumentsScreen(navController = navController)
    }
}

fun NavController.navigateToNavigationArgumentsNavGraph() =
    navigate(route = NavigationArgumentsNavGraphRoute)

@Serializable
internal data object NavigationArgumentsScreenRoute

internal fun NavGraphBuilder.navigationArgumentsScreen(navController: NavController) {
    composable<NavigationArgumentsScreenRoute> {
        NavigationArgumentsScreen(
            onNavigateUpClick = dropUnlessResumed(block = navController::navigateUp),
        )
    }
}
