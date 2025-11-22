package io.github.daiji256.showcase.feature.localsnackbarhoststate

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LocalSnackbarHostStateNavKey : NavKey

fun EntryProviderScope<NavKey>.localSnackbarHostState(
    onNavigateUpClick: () -> Unit,
) {
    entry<LocalSnackbarHostStateNavKey> {
        LocalSnackbarHostStateScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
