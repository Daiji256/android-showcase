package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries

/**
 * Remembers a list of [NavEntry] decorated with the list of [entryDecorators],
 * and returns a list of decorated active [NavEntry].
 *
 * @param T the type of the tree
 * @param tree the root navigation tree
 * @param entryDecorators the [NavEntryDecorator]s that are providing data to the content
 * @param entryProvider a function that returns the [NavEntry] for a given key
 * @return a list of decorated active [NavEntry]
 */
@Composable
fun <T : NavKey> rememberNavTreeEntries(
    tree: NavNode<T>,
    entryDecorators: List<@JvmSuppressWildcards NavEntryDecorator<T>> = listOf(),
    entryProvider: (T) -> NavEntry<T>,
): List<NavEntry<T>> {
    val keys by rememberNavTreeKeys(tree)

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

private data class NavTreeKeys<T : NavKey>(
    val active: List<T>,
    val inactive: List<T>,
) {
    /**
     * combined list to remember the state of all keys
     */
    val all: List<T> = inactive + active
}

@Composable
private fun <T : NavKey> rememberNavTreeKeys(
    tree: NavNode<T>,
): State<NavTreeKeys<T>> =
    remember(tree) {
        derivedStateOf {
            val active = mutableListOf<T>()
            val inactive = mutableListOf<T>()

            // Stack of (isActive, node)
            val pending = ArrayDeque<Pair<Boolean, NavNode<T>>>()
            pending.addLast(true to tree)

            while (pending.isNotEmpty()) {
                val (isActive, node) = pending.removeLast()
                when (node) {
                    is NavNode.Leaf ->
                        if (isActive) {
                            active.add(node.key)
                        } else {
                            inactive.add(node.key)
                        }

                    is NavNode.Stack ->
                        node.children.reversed().forEach {
                            pending.addLast(isActive to it)
                        }

                    is NavNode.Select ->
                        node.children.forEach {
                            // a node is active only if it is selected and on an active path
                            val isChildActive = isActive && node.selected == it.key
                            pending.addLast(isChildActive to it.value)
                        }
                }
            }

            NavTreeKeys(active = active, inactive = inactive)
        }
    }
