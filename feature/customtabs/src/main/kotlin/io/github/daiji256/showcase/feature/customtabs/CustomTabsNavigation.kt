package io.github.daiji256.showcase.feature.customtabs

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object CustomTabsNavKey : NavKey

fun EntryProviderScope<NavKey>.customTabs(
    onNavigateUpClick: () -> Unit,
) {
    entry<CustomTabsNavKey> {
        CustomTabsScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
