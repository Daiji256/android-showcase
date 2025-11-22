package io.github.daiji256.showcase.feature.navigationarguments

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object NavigationArgumentsNavKey : NavKey

fun EntryProviderScope<NavKey>.navigationArguments(
    onNavigateUpClick: () -> Unit,
) {
    entry<NavigationArgumentsNavKey> {
        NavigationArgumentsScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
