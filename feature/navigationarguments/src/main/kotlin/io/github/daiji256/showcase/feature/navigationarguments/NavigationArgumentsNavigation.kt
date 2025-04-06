package io.github.daiji256.showcase.feature.navigationarguments

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.daiji256.showcase.feature.navigationarguments.child1.child1Screen
import io.github.daiji256.showcase.feature.navigationarguments.child1.navigateToChild1Screen
import io.github.daiji256.showcase.feature.navigationarguments.child2.child2Screen
import io.github.daiji256.showcase.feature.navigationarguments.child2.navigateToChild2Screen
import kotlinx.serialization.Serializable

@Serializable
internal data object NavigationArgumentsNavGraphRoute

fun NavGraphBuilder.navigationArgumentsNavGraph(navController: NavController) {
    navigation<NavigationArgumentsNavGraphRoute>(
        startDestination = NavigationArgumentsScreenRoute,
    ) {
        navigationArgumentsScreen(navController = navController)
        child1Screen(navController = navController)
        child2Screen(navController = navController)
    }
}

fun NavController.navigateToNavigationArgumentsNavGraph() =
    navigate(route = NavigationArgumentsNavGraphRoute)

@Serializable
internal data object NavigationArgumentsScreenRoute

internal fun NavGraphBuilder.navigationArgumentsScreen(navController: NavController) {
    composable<NavigationArgumentsScreenRoute> {
        NavigationArgumentsScreen(
            onNavigateUpClick = navController::navigateUp,
            onNavigateToChild1ScreenClick = navController::navigateToChild1Screen,
            onNavigateToChild2ScreenClick = navController::navigateToChild2Screen,
        )
    }
}
