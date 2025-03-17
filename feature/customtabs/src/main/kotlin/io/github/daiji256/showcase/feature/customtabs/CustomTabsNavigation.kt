package io.github.daiji256.showcase.feature.customtabs

import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object CustomTabsScreenRoute

fun NavGraphBuilder.customTabsScreen(navController: NavController) {
    composable<CustomTabsScreenRoute> {
        CustomTabsScreen(
            onNavigateUpClick = dropUnlessResumed(block = navController::navigateUp),
        )
    }
}

fun NavController.navigateToCustomTabsScreen() = navigate(route = CustomTabsScreenRoute)
