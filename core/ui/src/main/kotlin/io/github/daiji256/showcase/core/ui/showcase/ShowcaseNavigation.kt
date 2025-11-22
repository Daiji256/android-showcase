package io.github.daiji256.showcase.core.ui.showcase

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.core.ui.feature.FeatureSummary
import kotlinx.collections.immutable.ImmutableList
import kotlinx.serialization.Serializable

@Serializable
data object ShowcaseNavKey : NavKey

fun EntryProviderScope<NavKey>.showcase(
    features: ImmutableList<FeatureSummary>,
) {
    entry<ShowcaseNavKey> {
        ShowcaseScreen(features = features)
    }
}
