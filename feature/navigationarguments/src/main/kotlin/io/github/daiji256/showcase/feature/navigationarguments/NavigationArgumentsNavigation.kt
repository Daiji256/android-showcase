package io.github.daiji256.showcase.feature.navigationarguments

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import io.github.daiji256.showcase.feature.navigationarguments.argssender.ArgsSenderScreenRoute
import io.github.daiji256.showcase.feature.navigationarguments.argssender.argsSenderScreen
import io.github.daiji256.showcase.feature.navigationarguments.diargsreceiver.diArgsReceiverScreen
import io.github.daiji256.showcase.feature.navigationarguments.directargsreceiver.directArgsReceiverScreen
import io.github.daiji256.showcase.feature.navigationarguments.viewmodelargsreceiver.viewModelArgsReceiverScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object NavigationArgumentsNavGraphRoute

fun NavGraphBuilder.navigationArgumentsNavGraph(navController: NavController) {
    navigation<NavigationArgumentsNavGraphRoute>(
        startDestination = ArgsSenderScreenRoute,
    ) {
        argsSenderScreen(navController = navController)
        directArgsReceiverScreen(navController = navController)
        viewModelArgsReceiverScreen(navController = navController)
        diArgsReceiverScreen(navController = navController)
    }
}

fun NavController.navigateToNavigationArgumentsNavGraph() =
    navigate(route = NavigationArgumentsNavGraphRoute)
