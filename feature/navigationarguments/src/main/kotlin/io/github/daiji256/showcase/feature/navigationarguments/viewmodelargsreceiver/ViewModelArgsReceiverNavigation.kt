package io.github.daiji256.showcase.feature.navigationarguments.viewmodelargsreceiver

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class ViewModelArgsReceiverScreenRoute(val arg: String)

internal fun NavGraphBuilder.viewModelArgsReceiverScreen(navController: NavController) {
    composable<ViewModelArgsReceiverScreenRoute> {
        ViewModelArgsReceiverScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

internal fun NavController.navigateToViewModelArgsReceiverScreen(arg: String) =
    navigate(route = ViewModelArgsReceiverScreenRoute(arg = arg))
