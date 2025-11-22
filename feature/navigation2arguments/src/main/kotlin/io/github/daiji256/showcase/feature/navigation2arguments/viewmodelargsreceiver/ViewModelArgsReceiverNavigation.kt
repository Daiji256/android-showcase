package io.github.daiji256.showcase.feature.navigation2arguments.viewmodelargsreceiver

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data class ViewModelArgsReceiverRoute(val arg: String)

internal fun NavGraphBuilder.viewModelArgsReceiver(
    onNavigateUpClick: () -> Unit,
) {
    composable<ViewModelArgsReceiverRoute> {
        ViewModelArgsReceiverScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
