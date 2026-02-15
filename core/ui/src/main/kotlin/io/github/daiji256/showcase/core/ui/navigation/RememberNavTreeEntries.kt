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
    entryDecoratorsProvider: @Composable (
        NavNode.Stack<T>,
    ) -> List<@JvmSuppressWildcards NavEntryDecorator<T>> = { listOf() },
    entryProvider: (T) -> NavEntry<T>,
): List<NavEntry<T>> {
    val stacks: Set<NavNode.Stack<T>> by rememberUpdatedState(tree.getStacks())

    // cache past stacks to allow empty lists for rememberDecoratedNavEntries
    val stacksCache: SnapshotStateSet<NavNode.Stack<T>> = remember(tree) { mutableStateSetOf() }

    // combine cached stacks with current stacks
    val allStacks: Set<NavNode.Stack<T>> by remember(tree) {
        derivedStateOf {
            stacksCache.addAll(stacks)
            stacksCache
        }
    }

    val allEntries = allStacks.associateWith { stack ->
        key(stack.id) {
            // treat stacks not in current set as empty to trigger pops
            val backStack = if (stack in stacks) {
                stack.children.mapNotNull { (it as? NavNode.Key)?.navKey }
            } else {
                emptyList()
            }

            val entries = rememberDecoratedNavEntries(
                backStack = backStack,
                entryDecorators = entryDecoratorsProvider(stack),
                entryProvider = entryProvider,
            )

            // remove cached stack after pop processing completes
            DisposableEffect(stack, entries.isEmpty()) {
                if (entries.isEmpty()) {
                    stacksCache.remove(stack)
                }
                onDispose {}
            }

            entries
        }
    }

    // returns the active entries
    return tree.getActiveStacks()
        .flatMap { allEntries[it] ?: emptyList() }
}

/**
 * Get all [NavNode.Stack] nodes in the [NavNode].
 */
private fun <T : NavKey> NavNode<T>.getStacks(): Set<NavNode.Stack<T>> =
    when (this) {
        is NavNode.Key ->
            emptySet()

        is NavNode.Stack ->
            buildSet {
                add(this@getStacks)
                children.forEach {
                    addAll(it.getStacks())
                }
            }

        is NavNode.Select ->
            buildSet {
                children.values.forEach {
                    addAll(it.getStacks())
                }
            }
    }

/**
 * Get active [NavNode.Stack] nodes in the [NavNode].
 */
private fun <T : NavKey> NavNode<T>.getActiveStacks(): List<NavNode.Stack<T>> =
    when (this) {
        is NavNode.Key ->
            emptyList()

        is NavNode.Stack ->
            buildList {
                add(this@getActiveStacks)
                children.forEach {
                    addAll(it.getActiveStacks())
                }
            }

        is NavNode.Select ->
            selectedChild.getActiveStacks()
    }
