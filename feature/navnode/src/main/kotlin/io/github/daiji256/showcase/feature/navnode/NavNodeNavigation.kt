package io.github.daiji256.showcase.feature.navnode

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object NavNodeNavKey : NavKey

fun EntryProviderScope<NavKey>.navNode(
    onNavigateUpClick: () -> Unit,
) {
    entry<NavNodeNavKey> {
        NavNodeScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
