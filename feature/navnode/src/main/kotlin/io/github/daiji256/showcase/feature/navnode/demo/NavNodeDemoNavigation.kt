package io.github.daiji256.showcase.feature.navnode.demo

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class NavNodeDemoNavKey(val initial: DemoInitial) : NavKey

fun EntryProviderScope<NavKey>.navNodeDemo() {
    entry<NavNodeDemoNavKey> { key ->
        NavNodeDemoScreen(initial = key.initial)
    }
}
