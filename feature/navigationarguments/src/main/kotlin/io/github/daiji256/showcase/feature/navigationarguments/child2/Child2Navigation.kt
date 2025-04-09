package io.github.daiji256.showcase.feature.navigationarguments.child2

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class Child2ScreenRoute(val argument: String)

internal fun NavGraphBuilder.child2Screen(navController: NavController) {
    composable<Child2ScreenRoute> {
        Child2Screen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

internal fun NavController.navigateToChild2Screen(argument: String) =
    navigate(route = Child2ScreenRoute(argument = argument))
