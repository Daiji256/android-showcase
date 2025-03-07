package io.github.daiji256.showcase.feature.customtabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
internal data object CustomTabsScreenRoute

fun NavGraphBuilder.customTabsScreen() {
    composable<CustomTabsScreenRoute> {
        CustomTabsScreen()
    }
}

fun NavController.navigateToCustomTabsScreen() = navigate(route = CustomTabsScreenRoute)
