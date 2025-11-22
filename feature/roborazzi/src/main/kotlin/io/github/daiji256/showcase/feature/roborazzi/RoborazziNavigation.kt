package io.github.daiji256.showcase.feature.roborazzi

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object RoborazziNavKey : NavKey

fun EntryProviderScope<NavKey>.roborazzi(
    onNavigateUpClick: () -> Unit,
) {
    entry<RoborazziNavKey> {
        RoborazziScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
