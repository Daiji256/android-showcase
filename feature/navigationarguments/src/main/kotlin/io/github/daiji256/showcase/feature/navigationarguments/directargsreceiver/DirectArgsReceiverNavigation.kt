package io.github.daiji256.showcase.feature.navigationarguments.directargsreceiver

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
internal data class DirectArgsReceiverScreenRoute(val arg: String)

internal fun NavGraphBuilder.directArgsReceiverScreen(navController: NavController) {
    composable<DirectArgsReceiverScreenRoute> { entry ->
        val route = entry.toRoute<DirectArgsReceiverScreenRoute>()
        DirectArgsReceiverScreen(
            arg = route.arg,
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

internal fun NavController.navigateToDirectArgsReceiverScreen(arg: String) =
    navigate(route = DirectArgsReceiverScreenRoute(arg = arg))
