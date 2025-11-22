package io.github.daiji256.showcase.feature.navigation2arguments.argssender

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object ArgsSenderRoute

internal fun NavGraphBuilder.argsSender(
    onNavigateUpClick: () -> Unit,
    onReceiveArgDirectlyClick: (arg: String) -> Unit,
    onReceiveArgViaViewModelClick: (arg: String) -> Unit,
    onReceiveArgViaDiClick: (arg: String) -> Unit,
) {
    composable<ArgsSenderRoute> {
        ArgsSenderScreen(
            onNavigateUpClick = onNavigateUpClick,
            onReceiveArgDirectlyClick = onReceiveArgDirectlyClick,
            onReceiveArgViaViewModelClick = onReceiveArgViaViewModelClick,
            onReceiveArgViaDiClick = onReceiveArgViaDiClick,
        )
    }
}
