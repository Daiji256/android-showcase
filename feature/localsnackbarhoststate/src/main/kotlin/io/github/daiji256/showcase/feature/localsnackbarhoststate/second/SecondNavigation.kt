package io.github.daiji256.showcase.feature.localsnackbarhoststate.second

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
internal data object SecondNavKey : NavKey

internal fun EntryProviderScope<NavKey>.second(
    onNavigateUpClick: () -> Unit,
) {
    entry<SecondNavKey> {
        SecondScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
