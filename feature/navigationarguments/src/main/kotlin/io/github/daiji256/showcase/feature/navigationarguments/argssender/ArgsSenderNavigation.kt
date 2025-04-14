package io.github.daiji256.showcase.feature.navigationarguments.argssender

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver.navigateToDiArgsReceiverScreen
import io.github.daiji256.showcase.feature.navigationarguments.directargsreceiver.navigateToDirectArgsReceiverScreen
import io.github.daiji256.showcase.feature.navigationarguments.viewmodelargsreceiver.navigateToViewModelArgsReceiverScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object ArgsSenderScreenRoute

internal fun NavGraphBuilder.argsSenderScreen(navController: NavController) {
    composable<ArgsSenderScreenRoute> {
        ArgsSenderScreen(
            onNavigateUpClick = navController::navigateUp,
            onNavigateToDirectArgsReceiverScreenClick =
            navController::navigateToDirectArgsReceiverScreen,
            onNavigateToViewModelArgsReceiverScreenClick =
            navController::navigateToViewModelArgsReceiverScreen,
            onNavigateToDiArgsReceiverScreenClick = navController::navigateToDiArgsReceiverScreen,
        )
    }
}
