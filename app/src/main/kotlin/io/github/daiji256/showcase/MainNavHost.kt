package io.github.daiji256.showcase

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.daiji256.showcase.feature.ktlint.ktlintScreen
import io.github.daiji256.showcase.feature.roborazzi.roborazziScreen
import io.github.daiji256.showcase.feature.showcase.ShowcaseScreenRoute
import io.github.daiji256.showcase.feature.showcase.showcaseScreen

@Composable
internal fun MainNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        startDestination = ShowcaseScreenRoute,
        navController = navController,
        modifier = modifier,
    ) {
        ktlintScreen()
        roborazziScreen()
        showcaseScreen()
    }
}
