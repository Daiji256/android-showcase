package io.github.daiji256.showcase.feature.localsnackbarhoststate.second

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LocalSnackbarHostStateSecondNavKey : NavKey

internal fun EntryProviderScope<NavKey>.localSnackbarHostStateSecond() {
    entry<LocalSnackbarHostStateSecondNavKey> {
        LocalSnackbarHostStateSecondScreen()
    }
}
