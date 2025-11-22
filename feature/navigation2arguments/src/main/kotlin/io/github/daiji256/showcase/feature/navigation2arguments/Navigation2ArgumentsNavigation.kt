package io.github.daiji256.showcase.feature.navigation2arguments

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Navigation2ArgumentsNavKey : NavKey

fun EntryProviderScope<NavKey>.navigation2Arguments(
    onNavigateUpClick: () -> Unit,
) {
    entry<Navigation2ArgumentsNavKey> {
        Navigation2ArgumentsScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
