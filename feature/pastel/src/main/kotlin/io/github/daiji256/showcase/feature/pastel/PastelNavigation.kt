package io.github.daiji256.showcase.feature.pastel

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object PastelNavKey : NavKey

fun EntryProviderScope<NavKey>.pastel() {
    entry<PastelNavKey> {
        PastelScreen()
    }
}
