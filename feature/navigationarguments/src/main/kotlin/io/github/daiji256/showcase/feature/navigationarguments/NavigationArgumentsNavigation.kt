package io.github.daiji256.showcase.feature.navigationarguments

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import io.github.daiji256.showcase.feature.navigationarguments.argumentsender.ArgumentSenderScreenRoute
import io.github.daiji256.showcase.feature.navigationarguments.argumentsender.argumentSenderScreen
import io.github.daiji256.showcase.feature.navigationarguments.child1.child1Screen
import io.github.daiji256.showcase.feature.navigationarguments.child2.child2Screen
import io.github.daiji256.showcase.feature.navigationarguments.child3.child3Screen
import kotlinx.serialization.Serializable

@Serializable
internal data object NavigationArgumentsNavGraphRoute

fun NavGraphBuilder.navigationArgumentsNavGraph(navController: NavController) {
    navigation<NavigationArgumentsNavGraphRoute>(
        startDestination = ArgumentSenderScreenRoute,
    ) {
        argumentSenderScreen(navController = navController)
        child1Screen(navController = navController)
        child2Screen(navController = navController)
        child3Screen(navController = navController)
    }
}

fun NavController.navigateToNavigationArgumentsNavGraph() =
    navigate(route = NavigationArgumentsNavGraphRoute)
