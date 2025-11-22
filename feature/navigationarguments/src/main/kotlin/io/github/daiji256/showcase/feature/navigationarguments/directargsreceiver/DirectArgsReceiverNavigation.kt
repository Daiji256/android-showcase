package io.github.daiji256.showcase.feature.navigationarguments.directargsreceiver

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
internal data class DirectArgsReceiverRoute(val arg: String)

internal fun NavGraphBuilder.directArgsReceiver(
    onNavigateUpClick: () -> Unit,
) {
    composable<DirectArgsReceiverRoute> { entry ->
        val route = entry.toRoute<DirectArgsReceiverRoute>()
        DirectArgsReceiverScreen(
            arg = route.arg,
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
