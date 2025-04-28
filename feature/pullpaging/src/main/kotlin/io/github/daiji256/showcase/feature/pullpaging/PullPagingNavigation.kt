package io.github.daiji256.showcase.feature.pullpaging

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object PullPagingScreenRoute

fun NavGraphBuilder.pullPagingScreen(navController: NavController) {
    composable<PullPagingScreenRoute> {
        PullPagingScreen(
            onNavigateUpClick = navController::navigateUp,
        )
    }
}

fun NavController.navigateToPullPagingScreen() = navigate(route = PullPagingScreenRoute)
