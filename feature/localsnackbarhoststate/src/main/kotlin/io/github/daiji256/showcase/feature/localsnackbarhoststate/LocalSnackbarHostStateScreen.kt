package io.github.daiji256.showcase.feature.localsnackbarhoststate

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import io.github.daiji256.showcase.feature.localsnackbarhoststate.first.FirstNavKey
import io.github.daiji256.showcase.feature.localsnackbarhoststate.first.first
import io.github.daiji256.showcase.feature.localsnackbarhoststate.second.SecondNavKey
import io.github.daiji256.showcase.feature.localsnackbarhoststate.second.second

@Composable
internal fun LocalSnackbarHostStateScreen(
    onNavigateUpClick: () -> Unit,
) {
    val backStack = rememberNavBackStack(FirstNavKey)
    NavDisplay(
        backStack = backStack,
        // TODO: Transition
        entryProvider = entryProvider {
            first(
                onNavigateUpClick = onNavigateUpClick,
                onNavigateToSecondClick = { backStack.add(SecondNavKey) },
            )
            second(
                onNavigateUpClick = { backStack.removeLastOrNull() },
            )
        },
    )
}
