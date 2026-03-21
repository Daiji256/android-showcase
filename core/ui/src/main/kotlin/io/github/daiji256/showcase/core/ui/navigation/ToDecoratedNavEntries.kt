package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries

/**
 * Convert the [NavState] to a list of decorated active [NavEntry].
 *
 * @param entryDecorators the [NavEntryDecorator]s that are providing data to the content
 * @param entryProvider a function that returns the [NavEntry] for a given key
 * @return a list of decorated active [NavEntry]
 */
@Composable
fun NavState.toDecoratedNavEntries(
    entryDecorators: List<@JvmSuppressWildcards NavEntryDecorator<NavKey>> = listOf(),
    entryProvider: (NavKey) -> NavEntry<NavKey>,
): List<NavEntry<NavKey>> {
    val keys by remember(root) {
        derivedStateOf { NavNodeKeys.fromRoot(root) }
    }

    // process all keys to remember the state of all entries in the back stack
    val allEntries = rememberDecoratedNavEntries(
        backStack = keys.all,
        entryDecorators = entryDecorators,
        entryProvider = entryProvider,
    )

    return remember(allEntries, keys) {
        allEntries.takeLast(keys.active.size)
    }
}
