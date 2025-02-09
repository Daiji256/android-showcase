package io.github.daiji256.showcase.feature.showcase

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ShowcaseScreenRoute

fun NavGraphBuilder.showcaseScreen() {
    composable<ShowcaseScreenRoute> {
        ShowcaseScreen()
    }
}

fun NavController.navigateToShowcaseScreen() = navigate(route = ShowcaseScreenRoute)
