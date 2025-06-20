package io.github.daiji256.showcase.feature.localsnackbarhoststate

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import io.github.daiji256.showcase.feature.localsnackbarhoststate.first.FirstScreenRoute
import io.github.daiji256.showcase.feature.localsnackbarhoststate.first.firstScreen
import io.github.daiji256.showcase.feature.localsnackbarhoststate.second.secondScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object LocalSnackbarHostStateNavGraphRoute

fun NavGraphBuilder.localSnackbarHostStateNavGraph(navController: NavController) {
    navigation<LocalSnackbarHostStateNavGraphRoute>(
        startDestination = FirstScreenRoute,
    ) {
        firstScreen(navController = navController)
        secondScreen(navController = navController)
    }
}

fun NavController.navigateToLocalSnackbarHostStateNavGraph() =
    navigate(route = LocalSnackbarHostStateNavGraphRoute)
