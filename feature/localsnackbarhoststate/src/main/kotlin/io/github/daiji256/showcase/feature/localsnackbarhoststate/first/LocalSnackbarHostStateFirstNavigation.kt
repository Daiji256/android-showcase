package io.github.daiji256.showcase.feature.localsnackbarhoststate.first

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LocalSnackbarHostStateFirstNavKey : NavKey

internal fun EntryProviderScope<NavKey>.localSnackbarHostStateFirst() {
    entry<LocalSnackbarHostStateFirstNavKey> {
        LocalSnackbarHostStateFirstScreen()
    }
}
