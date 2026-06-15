package io.github.daiji256.showcase.feature.mixedfonts

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object MixedFontsNavKey : NavKey

fun EntryProviderScope<NavKey>.mixedFonts() {
    entry<MixedFontsNavKey> {
        MixedFontsScreen()
    }
}
