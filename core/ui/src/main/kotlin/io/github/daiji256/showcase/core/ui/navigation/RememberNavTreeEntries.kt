package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshots.SnapshotStateSet
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import kotlin.uuid.Uuid

/**
 * Remembers a list of [NavEntry] decorated with the list of [entryDecoratorsProvider],
 * and returns a list of decorated active [NavEntry].
 *
 * @param T the type of the tree
 * @param tree the root navigation stack
 * @param entryDecoratorsProvider a provider of the [NavEntryDecorator]s
 * @param entryProvider a function that returns the [NavEntry] for a given key
 * @return a list of decorated active [NavEntry]
 */
@Composable
fun <T : NavKey> rememberNavTreeEntries(
    tree: NavNode.Stack<T>,
    entryDecoratorsProvider: @Composable () -> List<NavEntryDecorator<T>> = { listOf() },
    entryProvider: (T) -> NavEntry<T>,
): List<NavEntry<T>> {
    val stacks: Map<Uuid, NavNode.Stack<T>> by rememberUpdatedState(tree.getStacks())

    // cache past stack IDs to allow empty lists for rememberDecoratedNavEntries
    val stackIdsCache: SnapshotStateSet<Uuid> = remember(tree) { mutableStateSetOf() }

    // combine cached stack IDs with current stack IDs
    val allStackIds: Set<Uuid> by remember(tree) {
        derivedStateOf {
            stackIdsCache.addAll(stacks.keys)
            stackIdsCache
        }
    }

    val allEntries = allStackIds.associateWith { id ->
        key(id) {
            val entries = rememberDecoratedNavEntries(
                // if not in the current stack, treated as empty and triggers a pop
                backStack = stacks[id]?.getBackStack() ?: emptyList(),
                entryDecorators = entryDecoratorsProvider(),
                entryProvider = entryProvider,
            )

            // remove cached stack id after pop processing completes
            DisposableEffect(id, entries.isEmpty()) {
                if (entries.isEmpty()) {
                    stackIdsCache.remove(id)
                }
                onDispose {}
            }

            entries
        }
    }

    // returns the active entries
    return tree.getActiveStackIds()
        .flatMap { id -> allEntries[id] ?: emptyList() }
}

private fun <T : NavKey> NavNode<T>.getStacks(): Map<Uuid, NavNode.Stack<T>> =
    when (this) {
        is NavNode.Key ->
            emptyMap()

        is NavNode.Stack ->
            buildMap {
                put(this@getStacks.id, this@getStacks)
                children.forEach {
                    putAll(it.getStacks())
                }
            }

        is NavNode.Select ->
            buildMap {
                children.values.forEach {
                    putAll(it.getStacks())
                }
            }
    }

private fun <T : NavKey> NavNode<T>.getActiveStackIds(): List<Uuid> =
    when (this) {
        is NavNode.Key ->
            emptyList()

        is NavNode.Stack ->
            buildList {
                add(this@getActiveStackIds.id)
                children.forEach {
                    addAll(it.getActiveStackIds())
                }
            }

        is NavNode.Select ->
            selectedChild.getActiveStackIds()
    }

private fun <T : NavKey> NavNode.Stack<T>.getBackStack(): List<T> =
    this.children.mapNotNull { (it as? NavNode.Key)?.navKey }
