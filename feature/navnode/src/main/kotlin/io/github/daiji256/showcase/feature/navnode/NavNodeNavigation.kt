package io.github.daiji256.showcase.feature.navnode

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import io.github.daiji256.showcase.feature.navnode.demo.navNodeDemo
import kotlinx.serialization.Serializable

@Serializable
data object NavNodeNavKey : NavKey

fun EntryProviderScope<NavKey>.navNode() {
    entry<NavNodeNavKey> {
        NavNodeScreen()
    }
    navNodeDemo()
}
