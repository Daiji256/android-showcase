package io.github.daiji256.showcase.feature.localsnackbarhoststate.first

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal data object FirstNavKey : NavKey

internal fun EntryProviderScope<NavKey>.first(
    onNavigateUpClick: () -> Unit,
    onNavigateToSecondClick: () -> Unit,
) {
    entry<FirstNavKey> {
        FirstScreen(
            onNavigateUpClick = onNavigateUpClick,
            onNavigateToSecondClick = onNavigateToSecondClick,
        )
    }
}
