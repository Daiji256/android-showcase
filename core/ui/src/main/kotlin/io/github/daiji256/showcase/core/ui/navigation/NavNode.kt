package io.github.daiji256.showcase.core.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavKey

/**
 * Represents a node in a navigation tree.
 *
 * @param T the type of the tree
 */
sealed interface NavNode<T : NavKey> {
    /**
     * Attempts to navigate to the [route].
     *
     * @param route the destination [NavNode]
     * @return `true` if navigation was handled, `false` otherwise.
     */
    fun navigate(route: NavNode<T>): Boolean

    /**
     * Attempts to navigate back.
     *
     * @return `true` if back navigation was handled, `false` otherwise.
     */
    fun back(): Boolean

    /**
     * Leaf node that represent navigation keys.
     *
     * @property navKey The associated [NavKey].
     */
    class Key<T : NavKey>(
        val navKey: T,
    ) : NavNode<T> {
        override fun navigate(route: NavNode<T>) = false

        override fun back(): Boolean = false
    }

    /**
     * Stack node that manages a list of child nodes.
     *
     * @param children the initial list of child nodes
     */
    class Stack<T : NavKey>(
        vararg children: NavNode<T>,
    ) : NavNode<T> {
        private val _children = mutableStateListOf(*children)

        /**
         * The list of child nodes.
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
     * @param selected the initially selected key
     * @param children the list of available keys
     */
    class Select<T : NavKey>(
        selected: T,
        vararg children: T,
    ) : NavNode<T> {
        /**
         * The selected child key.
         */
        var selected by mutableStateOf(selected)
            private set

        /**
         * The list of child stacks.
         */
        val children = children.associateWith { Stack(Key(navKey = it)) }

        /**
         * The selected child stack.
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
