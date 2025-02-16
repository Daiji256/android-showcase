package io.github.daiji256.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.daiji256.showcase.feature.ktlint.KtlintSummary
import io.github.daiji256.showcase.feature.ktlint.ktlintScreen
import io.github.daiji256.showcase.feature.roborazzi.RoborazziSummary
import io.github.daiji256.showcase.feature.roborazzi.roborazziScreen
import io.github.daiji256.showcase.feature.showcase.ShowcaseScreenRoute
import io.github.daiji256.showcase.feature.showcase.showcaseScreen
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        startDestination = ShowcaseScreenRoute,
        navController = navController,
        modifier = modifier,
    ) {
        ktlintScreen()
        roborazziScreen()
        showcaseScreen(
            features = persistentListOf(
                KtlintSummary,
                RoborazziSummary,
            ),
        )
    }
}
