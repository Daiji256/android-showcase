package io.github.daiji256.showcase.feature.ktlint

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object KtlintNavKey : NavKey

fun EntryProviderScope<NavKey>.ktlint(
    onNavigateUpClick: () -> Unit,
) {
    entry<KtlintNavKey> {
        KtlintScreen(
            onNavigateUpClick = onNavigateUpClick,
        )
    }
}
