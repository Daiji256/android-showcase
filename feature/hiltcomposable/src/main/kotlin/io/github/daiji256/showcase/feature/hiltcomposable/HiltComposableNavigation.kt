package io.github.daiji256.showcase.feature.hiltcomposable

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HiltComposableNavKey : NavKey

fun EntryProviderScope<NavKey>.hiltComposable(
    onNavigateUpClick: () -> Unit,
) {
    entry<HiltComposableNavKey> {
        HiltComposableScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
