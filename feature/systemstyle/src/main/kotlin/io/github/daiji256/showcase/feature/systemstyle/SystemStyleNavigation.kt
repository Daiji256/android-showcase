package io.github.daiji256.showcase.feature.systemstyle

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object SystemStyleNavKey : NavKey

fun EntryProviderScope<NavKey>.systemStyle(
    onNavigateUpClick: () -> Unit,
) {
    entry<SystemStyleNavKey> {
        SystemStyleScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
