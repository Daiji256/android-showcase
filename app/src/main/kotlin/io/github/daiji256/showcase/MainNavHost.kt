package io.github.daiji256.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.daiji256.showcase.core.designsystem.theme.ShowcaseAnimations
import io.github.daiji256.showcase.core.ui.showcase.ShowcaseScreenRoute
import io.github.daiji256.showcase.core.ui.showcase.showcaseScreen
import io.github.daiji256.showcase.feature.customtabs.CustomTabsSummary
import io.github.daiji256.showcase.feature.customtabs.customTabsScreen
import io.github.daiji256.showcase.feature.ktlint.KtlintSummary
import io.github.daiji256.showcase.feature.ktlint.ktlintScreen
import io.github.daiji256.showcase.feature.roborazzi.RoborazziSummary
import io.github.daiji256.showcase.feature.roborazzi.roborazziScreen
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        startDestination = ShowcaseScreenRoute,
        navController = navController,
        enterTransition = ShowcaseAnimations.enterTransition,
        exitTransition = ShowcaseAnimations.exitTransition,
        popEnterTransition = ShowcaseAnimations.popEnterTransition,
        popExitTransition = ShowcaseAnimations.popExitTransition,
        modifier = modifier,
    ) {
        showcaseScreen(
            features = persistentListOf(
                CustomTabsSummary(navController = navController),
                KtlintSummary(navController = navController),
                RoborazziSummary(navController = navController),
            ),
        )
        customTabsScreen(navController = navController)
        ktlintScreen(navController = navController)
        roborazziScreen(navController = navController)
    }
}
