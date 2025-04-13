package io.github.daiji256.showcase.feature.navigationarguments.argumentsender

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.daiji256.showcase.feature.navigationarguments.child1.navigateToChild1Screen
import io.github.daiji256.showcase.feature.navigationarguments.child2.navigateToChild2Screen
import io.github.daiji256.showcase.feature.navigationarguments.child3.navigateToChild3Screen
import kotlinx.serialization.Serializable

@Serializable
internal data object ArgumentSenderScreenRoute

internal fun NavGraphBuilder.argumentSenderScreen(navController: NavController) {
    composable<ArgumentSenderScreenRoute> {
        ArgumentSenderScreen(
            onNavigateUpClick = navController::navigateUp,
            onNavigateToChild1ScreenClick = navController::navigateToChild1Screen,
            onNavigateToChild2ScreenClick = navController::navigateToChild2Screen,
            onNavigateToChild3ScreenClick = navController::navigateToChild3Screen,
        )
    }
}
