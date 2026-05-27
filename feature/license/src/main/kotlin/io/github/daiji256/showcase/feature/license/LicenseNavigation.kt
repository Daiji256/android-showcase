package io.github.daiji256.showcase.feature.license

import androidx.annotation.RawRes
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LicenseNavKey : NavKey

fun EntryProviderScope<NavKey>.license(@RawRes dependenciesResId: Int) {
    entry<LicenseNavKey> {
        LicenseScreen(
            dependenciesResId = dependenciesResId,
        )
    }
}
