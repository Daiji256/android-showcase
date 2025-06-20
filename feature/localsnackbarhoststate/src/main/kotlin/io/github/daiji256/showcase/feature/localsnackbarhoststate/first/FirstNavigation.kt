package io.github.daiji256.showcase.feature.localsnackbarhoststate.first

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.daiji256.showcase.feature.localsnackbarhoststate.second.navigateToSecondScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object FirstScreenRoute

internal fun NavGraphBuilder.firstScreen(navController: NavController) {
    composable<FirstScreenRoute> {
        FirstScreen(
            onNavigateUpClick = navController::navigateUp,
            onNavigateToSecondScreenClick = navController::navigateToSecondScreen,
        )
    }
}
