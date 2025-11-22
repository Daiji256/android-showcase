package io.github.daiji256.showcase.feature.navigationarguments

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.daiji256.showcase.feature.navigationarguments.argssender.ArgsSenderRoute
import io.github.daiji256.showcase.feature.navigationarguments.argssender.argsSender
import io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver.diArgsReceiver
import io.github.daiji256.showcase.feature.navigationarguments.directargsreceiver.DirectArgsReceiverRoute
import io.github.daiji256.showcase.feature.navigationarguments.directargsreceiver.directArgsReceiver
import io.github.daiji256.showcase.feature.navigationarguments.viewmodelargsreceiver.ViewModelArgsReceiverRoute
import io.github.daiji256.showcase.feature.navigationarguments.viewmodelargsreceiver.viewModelArgsReceiver

@Composable
internal fun NavigationArgumentsScreen(
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
                navController.navigate(route = DirectArgsReceiverRoute(arg = arg))
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
