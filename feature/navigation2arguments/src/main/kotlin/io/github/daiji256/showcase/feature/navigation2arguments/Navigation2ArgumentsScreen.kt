package io.github.daiji256.showcase.feature.navigation2arguments

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.daiji256.showcase.feature.navigation2arguments.argssender.ArgsSenderRoute
import io.github.daiji256.showcase.feature.navigation2arguments.argssender.argsSender
import io.github.daiji256.showcase.feature.navigation2arguments.diargsreceiver.DiArgsReceiverRoute
import io.github.daiji256.showcase.feature.navigation2arguments.diargsreceiver.diArgsReceiver
import io.github.daiji256.showcase.feature.navigation2arguments.directargsreceiver.DirectArgsReceiverRoute
import io.github.daiji256.showcase.feature.navigation2arguments.directargsreceiver.directArgsReceiver
import io.github.daiji256.showcase.feature.navigation2arguments.viewmodelargsreceiver.ViewModelArgsReceiverRoute
import io.github.daiji256.showcase.feature.navigation2arguments.viewmodelargsreceiver.viewModelArgsReceiver

@Composable
internal fun Navigation2ArgumentsScreen(
    onNavigateUpClick: () -> Unit,
) {
    val navController = rememberNavController()
    NavHost(
        startDestination = ArgsSenderRoute,
        navController = navController,
    ) {
        argsSender(
            onNavigateUpClick = onNavigateUpClick,
            onReceiveArgDirectlyClick = { arg ->
                navController.navigate(route = DirectArgsReceiverRoute(arg = arg))
            },
            onReceiveArgViaViewModelClick = { arg ->
                navController.navigate(route = ViewModelArgsReceiverRoute(arg = arg))
            },
            onReceiveArgViaDiClick = { arg ->
                navController.navigate(route = DiArgsReceiverRoute(arg = arg))
            },
        )
        directArgsReceiver(
            onNavigateUpClick = navController::navigateUp,
        )
        viewModelArgsReceiver(
            onNavigateUpClick = navController::navigateUp,
        )
        diArgsReceiver(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}
