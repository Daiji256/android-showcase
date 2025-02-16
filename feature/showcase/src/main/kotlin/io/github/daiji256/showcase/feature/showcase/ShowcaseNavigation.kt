package io.github.daiji256.showcase.feature.showcase

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import kotlinx.collections.immutable.ImmutableList
import kotlinx.serialization.Serializable

@Serializable
data object ShowcaseScreenRoute

fun NavGraphBuilder.showcaseScreen(
    features: ImmutableList<FeatureSummary>,
) {
    composable<ShowcaseScreenRoute> {
        ShowcaseScreen(features = features)
    }
}

fun NavController.navigateToShowcaseScreen() = navigate(route = ShowcaseScreenRoute)
