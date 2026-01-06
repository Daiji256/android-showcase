package io.github.daiji256.showcase.feature.license

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LicenseNavKey : NavKey

fun EntryProviderScope<NavKey>.license(
    onNavigateUpClick: () -> Unit,
) {
    entry<LicenseNavKey> {
        LicenseScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
