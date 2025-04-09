package io.github.daiji256.showcase.feature.navigationarguments.child1

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
internal data class Child1ScreenRoute(val argument: String)

internal fun NavGraphBuilder.child1Screen(navController: NavController) {
    composable<Child1ScreenRoute> { entry ->
        val route = entry.toRoute<Child1ScreenRoute>()
        Child1Screen(
            argument = route.argument,
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

internal fun NavController.navigateToChild1Screen(argument: String) =
    navigate(route = Child1ScreenRoute(argument = argument))
