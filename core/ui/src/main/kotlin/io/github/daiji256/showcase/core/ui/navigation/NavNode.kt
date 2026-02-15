package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

/**
 * Represents a node in a navigation tree.
 *
 * @param T the type of the tree
 */
@Serializable
sealed interface NavNode<T : NavKey> {
    /**
     * navigate to the [route]
     *
     * @param route the destination [NavNode]
     * @return `true` if navigation was handled, `false` otherwise
     */
    fun navigate(route: NavNode<T>): Boolean

    /**
     * navigate back
     *
     * @return `true` if back navigation was handled, `false` otherwise
     */
    fun back(): Boolean

    /**
     * Leaf node that represent navigation keys.
     *
     * @property navKey the associated [NavKey]
     */
    @Serializable(with = NavNodeKeySerializer::class)
    class Key<T : NavKey>(
        val navKey: T,
    ) : NavNode<T> {
        override fun navigate(route: NavNode<T>) = false

        override fun back(): Boolean = false
    }

    /**
     * Stack node that manages a list of child nodes.
     *
     * @param children the list of child nodes
     * @property id the unique identifier of the stack
     */
    @Serializable(with = NavNodeStackSerializer::class)
    class Stack<T : NavKey>(
        children: List<NavNode<T>>,
        val id: Uuid = Uuid.random(),
    ) : NavNode<T> {
        private val _children = children.toMutableStateList()

        /**
         * the list of child nodes
         */
        val children: List<NavNode<T>>
            get() = _children

        private val currentChild
            get() = children.lastOrNull() ?: error("No children")

        override fun navigate(route: NavNode<T>): Boolean =
            currentChild.navigate(route) || _children.add(route)

        override fun back(): Boolean {
            if (children.size <= 1) return false
            if (currentChild.back()) return true
            _children.removeAt(children.lastIndex)
            return true
        }
    }

    /**
     * Select node that manages a selection of child stacks.
     *
     * @param selected the selected key
     * @property children the map of child stacks
     */
    @Serializable(with = NavNodeSelectSerializer::class)
    class Select<T : NavKey>(
        selected: T,
        val children: Map<T, Stack<T>>,
    ) : NavNode<T> {
        /**
         * @param selected the selected key
         * @param children the set of child stack keys
         */
        constructor(
            selected: T,
            children: Set<T>,
        ) : this(
            selected = selected,
            children = children.associateWith { Stack(children = listOf(Key(navKey = it))) },
        )

        /**
         * the selected key
         */
        var selected by mutableStateOf(selected)
            private set

        /**
         * the selected child stack
         */
        val selectedChild
            get() = children[this@Select.selected] ?: error("No child for ${this@Select.selected}")

        override fun navigate(route: NavNode<T>): Boolean {
            if (route is Key && route.navKey in children.keys) {
                selected = route.navKey
                return true
            }
            return selectedChild.navigate(route)
        }

        override fun back(): Boolean = selectedChild.back()
    }
}
