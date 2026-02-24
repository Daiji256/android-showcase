package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Represents a node in a navigation tree.
 *
 * @param T the type of the tree
 */
@Serializable
sealed interface NavNode<T : NavKey> {
    /**
     * the associated [NavKey]
     */
    val key: T

    /**
     * the parent [NavNode]
     *
     * @see NavNode.navigateUp
     */
    val up: NavNode<T>?

    /**
     * Leaf node that represent navigation key.
     *
     * @param key the associated [NavKey]
     * @param up the parent [NavNode]
     */
    @Serializable(with = NavNodeLeafSerializer::class)
    class Leaf<T : NavKey>(
        override val key: T,
        override val up: NavNode<T>? = null,
    ) : NavNode<T>

    /**
     * Stack node that manages a list of child nodes.
     *
     * @param key the associated [NavKey]
     * @param children the list of child nodes
     * @param up the parent [NavNode]
     */
    @Serializable(with = NavNodeStackSerializer::class)
    class Stack<T : NavKey>(
        override val key: T,
        children: List<NavNode<T>>,
        override val up: NavNode<T>? = null,
    ) : NavNode<T> {
        init {
            require(children.isNotEmpty()) {
                "Stack must have at least one child"
            }
        }

        /**
         * the list of child nodes
         */
        val children: SnapshotStateList<NavNode<T>> = children.toMutableStateList()
    }

    /**
     * Select node that manages a selected child node.
     *
     * @param key the associated [NavKey]
     * @param selected the selected key
     * @param children the set of child nodes
     * @param up the parent [NavNode]
     */
    @Serializable(with = NavNodeSelectSerializer::class)
    class Select<T : NavKey>(
        override val key: T,
        selected: T,
        val children: Set<NavNode<T>>,
        override val up: NavNode<T>? = null,
    ) : NavNode<T> {
        init {
            require(children.any { it.key == selected }) {
                "Selected key must be in children"
            }
            require(children.size == children.distinctBy { it.key }.size) {
                "Children must have unique keys"
            }
        }

        /**
         * the selected key
         */
        var selected: T by mutableStateOf(selected)
    }
}
